$(document).ready(function () {
    prePopulateEditModal();
});

function prePopulateEditModal(){
    // Call to get all programs
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: encodeURI("/api/programs"),
        async: true,
        success: function (programResult) {
            // Populate the select with the entire list
            programResult._embedded.programs.forEach(p => {
                $('#programSelectEdit').append(new Option(p.name, p.name))
            });
        },
        error: function(xhr, ajaxOptions, thrownError){
            console.log(xhr);
            console.log(ajaxOptions);
            console.log(thrownError);
        }
    });

    // Call to get all learning outcomes
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: encodeURI("/api/learningoutcomes"),
        async: true,
        success: function (loResult) {
            // Populate the select with the entire list
            loResult._embedded.learningoutcomes.forEach(lo => {
                $('#loSelectEdit').append(new Option(lo.name, lo.name))
            });
        },
        error: function(xhr, ajaxOptions, thrownError){
            console.log(xhr);
            console.log(ajaxOptions);
            console.log(thrownError);
        }
    });
}

/**
 * Handle when the edit button is clicked
 * @param buttonClicked the edit button clicked
 */
function renderEditModal(buttonClicked){
    // Find the associated id for the row that was selected to edit
    let idClicked = $(buttonClicked).closest('tr').find('th')[0].innerText;

    // Set the hidden property in the modal to the clicked id
    document.getElementById("editId").value = idClicked;

    // Render the modal information
    getSelectedCourse(idClicked);
    getSelectedLearningOutcomes(idClicked);
    getSelectedPrograms(idClicked);
}

/**
 * Function handle for when a user has confirmed their
 * edited course information
 */
function editConfirm(){
    let course = {
        "name": $('#courseNameEdit').val(),
        "code": $('#courseCodeEdit').val(),
        "year": $('#courseYearSelectEdit').val()
    }

    // Append all the selected programs to the wrapper
    let programArray = [];
    $('#programSelectEdit').select2('data').forEach((program) =>{
        programArray.push(program.text);
    });

    // Append all the selected learning outcomes to the wrapper
    let loArray = [];
    $('#loSelectEdit').select2('data').forEach((lo) =>{
        loArray.push(lo.text);
    });

    // Build the wrapper object
    let requestWrapper = {};
    requestWrapper.course = course;
    requestWrapper.programList = programArray;
    requestWrapper.learningOutcomeList = loArray;

    let id = document.getElementById("editId").value;

    // Make the ajax call to update table
    $.ajax({
        type: "PUT",
        contentType: "application/json",
        url: encodeURI("/admin/" + id),
        data: JSON.stringify(requestWrapper),
        async: true,
        success: function (responseEntity) {
            // reloads only that HTML fragment
            $("#results").load(encodeURI("/adminResults"),
                function(){
                    $('#results-table').DataTable();    // reload sorting on table
                }
            );
        },
        error: function(xhr, ajaxOptions, thrownError){
            console.log(xhr);
            console.log(ajaxOptions);
            console.log(thrownError);
        }
    });
}

/**
 * Function call to find the selected course to edit
 */
function getSelectedCourse(id){
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: encodeURI("/api/courses/" + id),
        async: true,
        success: function (courseResult) {
            $('#courseNameEdit').val(courseResult.name);
            $('#courseCodeEdit').val(courseResult.code);
            $('#courseYearSelectEdit').val(courseResult.year);
        },
        error: function(xhr, ajaxOptions, thrownError){
            console.log(xhr);
            console.log(ajaxOptions);
            console.log(thrownError);
        }
    });
}

/**
 * Function call to find the corresponding learning outcomes
 * associated with the selected course to edit
 */
function getSelectedLearningOutcomes(id){
    // AJAX Call to find currently selected learning outcomes
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: encodeURI("/api/courses/" + id + "/learningOutcomes"),
        async: true,
        success: function (currentLO) {
            // Populate an array to fill the selected values
            selectedLO = [];
            currentLO._embedded.learningoutcomes.forEach(p => {
                selectedLO.push(p.name);
            });

            // Update and refresh the select
            $('#loSelectEdit').val(selectedLO);
            $('#loSelectEdit').trigger('change');
        },
        error: function(xhr, ajaxOptions, thrownError){
            console.log(xhr);
            console.log(ajaxOptions);
            console.log(thrownError);
        }
    });
}

/**
 * Function call to find the corresponding programs
 * associated with the selected course to edit
 */
function getSelectedPrograms(id){
    // AJAX Call to find currently selected programs
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: encodeURI("/api/courses/" + id + "/programs"),
        async: true,
        success: function (currentPrograms) {
            // Populate an array to fill the selected values
            selectedPrograms = [];
            currentPrograms._embedded.programs.forEach(p => {
                selectedPrograms.push(p.name);
            });

            // Update and refresh the select
            $('#programSelectEdit').val(selectedPrograms);
            $('#programSelectEdit').trigger('change');
        },
        error: function(xhr, ajaxOptions, thrownError){
            console.log(xhr);
            console.log(ajaxOptions);
            console.log(thrownError);
        }
    });
}


/**
 *   Adds a category based on inputted name using a post with a RequestWrapper. Processed and added in
 *   HomeController function of the same name.
 */
function addCategory(){
    let url = '/addCategory/';
    let cat = $("#categoryNameAdd").val();

    // Build the object to post
    let newData = {};
    newData.category = {"name": cat};

    //POST request with data
    post(url,newData);
}

/**
 *   Adds a learning outcome based on inputted name, and selected category using a post with a RequestWrapper.
 *   Processed and added in HomeController method of the same name
 */
function addLO(){
    let url = '/addLO/';
    let lo = $("#loNameAdd").val();
    let category = $("#loCategoryAdd").select2("val");

    // Build the object to post
    let newData = {};
    newData.learningOutcome = {"name": lo};
    newData.category = {"name": category};

    //POST request with data
    post(url,newData);
}

/**
 *   Adds a Program based on inputted name and, optionally, a selection of courses using a post with a RequestWrapper.
 *   Processed and added in HomeController method of the same name
 */
function addProgram(){
    let url = '/addProgram/';
    let program = $("#programNameAdd").val();
    let course = $("#programCourseAdd").select2("val");

    // Build the object to post
    let newData = {};
    newData.program = {"name": program};
    newData.courseList = course;

    //POST request with data
    post(url,newData);
}

/**
 *   Adds a Course based on inputted name, code, year, and  a selection of programs and learning outcomes using a post
 *   with a RequestWrapper. Processed and added in HomeController method of the same name
 */
function addCourse() {
    let url = '/addCourse/';
    let name = $("#courseNameAdd").val();
    let code = $("#codeAdd").val();
    let year = $("#yearAdd option:selected").text();
    let learningO = $("#loAdd").select2("val");
    let program = $("#programAdd").select2("val");

    // Build the object to post
    let newData = {};
    newData.course = {"name": name, "code": code, "year": year};
    newData.learningOutcomeList = learningO;
    newData.programList = program;

    //POST request with data
    post(url, newData);
}

/**
 *   takes a url and data to be posted using a RequestWrapper such that the home controller can format the data to be added
 */
function post(url, data){
    //post request, turn data into json format
    $.ajax({
        type: "POST",
        url: url,
        data: JSON.stringify(data),
        dataType : "text",
        contentType: "application/json",
        success: function(data) {
            //on success reload the page so that all field (including select2 drop downs) are updated.
            location.reload();
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            console.log("Failed to add new entry.")
        }
    });
}

/**
 *   Ensure the add button remains disabled until all required fields are inputted.
 *   Adding a course requires, name, code, learning outcome and program. year is always selected.
 *   the course code requires strictly 4 characters all of which are numbers. If that is not the case the field
 *   will appear red.
 */
function successCourse() {
    let button = document.getElementById('buttonCourse');
    let name = $("#courseNameAdd").val();
    let code = $("#codeAdd").val();
    let learningO = $("#loAdd option:selected").val();
    let program = $("#programAdd option:selected").val();

    //check if code is correct
    if(code.length <4 || code.length >4 || isNaN(code)==true){
        document.getElementById('codeAdd').style.backgroundColor = "rgb(246,215,218)";
        button.disabled = true;
    }
    //check if fields are not empty
    else if(name == "" || name==null || code =="" || code == null  || learningO =="" ||
        learningO == null || program =="" || program == null) {
        document.getElementById('codeAdd').style.backgroundColor = "rgb(255,255,255)";
        button.disabled = true;
    }
    //fields are filled and ready for adding
    else{
        document.getElementById('codeAdd').style.backgroundColor = "rgb(255,255,255)";
        button.disabled = false
    }
}

/**
 *   Ensure the add button remains disabled until all required fields are inputted.
 *   Adding a category requires a name to be inputted.
 */
function successCategory() {
    let button = document.getElementById('buttonCategory');
    let name = $("#categoryNameAdd").val();

    if(name == "" || name==null) {
        button.disabled = true;
    } else{
        button.disabled = false
    }
}

/**
 *   Ensure the add button remains disabled until all required fields are inputted.
 *   Adding a Learning Outcome requires a name and category to be inputted.
 */
function successLO() {
    let button = document.getElementById('buttonLO');
    let name = $("#loNameAdd").val();
    let cat = $("#loCategoryAdd").val();

    if(name == "" || name==null || cat == "" || cat == null) {
        button.disabled = true;
    } else{
        button.disabled = false
    }
}

/**
 *   Ensure the add button remains disabled until all required fields are inputted.
 *   Adding a Program requires a name. Including courses is optional.
 */
function successProgram() {
    let button = document.getElementById('buttonProgram');
    let name = $("#programNameAdd").val();

    if(name == "" || name==null) {
        button.disabled = true;
    } else{
        button.disabled = false
    }
}
