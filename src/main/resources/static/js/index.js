$(document).ready(function () {
    // Load jquery DataTables on results table (for sorting)
    $('#results-table').DataTable();

    // Starting jQuery library select2 for searchable select boxes https://select2.org/
    $('.select2').select2({
        placeholder: 'Choose...',
        allowClear: true
    });
});

/*
*   When the select for program changes, repopulates year select based on years available
 */
$('#programSelect').change( function() {
        var program = $("#programSelect option:selected").text();
        let newOptions = [];
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: encodeURI("/results/" + program),
            async: false,
            success: function (result) {
                newOptions = result;

            }
        });

        $('#yearSelect').empty().select2({
            data: newOptions
        });
    }
);

$(function () {
    $(".card").click(function () {
        // remove accent colours from all
        $(".card").removeClass("border-primary");
        $(".card-header").removeClass("bg-primary");

        // reset export button color
        $("#exportBtn").addClass("btn-info");
        $("#exportBtn").removeClass("btn-light");

        // add accent colors to clicked card only
        $(this).addClass("border-primary");
        $(this).find(".card-header").addClass("bg-primary");

        // if results clicked, change export color
        $(this).find("#exportBtn").removeClass("btn-info");
        $(this).find("#exportBtn").addClass("btn-light");

    });
});

/*
*   Reloads results table based on selected program and year
 */
function getResults() {
    var url = '/results/';
    var program = $("#programSelect option:selected").text();
    var year = $("#yearSelect option:selected").text();
    url += program + '/' + year;

    // reloads only that HTML fragment
    $("#results").load(encodeURI(url),
        function () {
            $('#results-table').DataTable();    // reload sorting on table
        }
    );
}

/*
*   Reloads results table based on selected program, year and courses
 */
function getFilterResults() {
    var url = '/results/';
    var program = $("#programSelect option:selected").text();
    var year = $("#yearSelect option:selected").text();
    var courses = $("#courseSelect").val();
    var categories = $("#categorySelect").val();

    url += program + '/' + year + '/' + '?courses=' + courses + '&' + 'categories=' + categories;
    
    // reloads only that HTML fragment
    $("#results").load(encodeURI(url),
        function () {
            $('#results-table').DataTable();    // reload sorting on table
        }
    );
}

/*
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

/*
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

/*
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

/*
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

/*
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

/*
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

/*
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

/*
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

/*
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

