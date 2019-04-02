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

function post(url, data){
    $.ajax({
        type: "POST",
        url: url,
        data: JSON.stringify(data),
        dataType : "text",
        contentType: "application/json",
        success: function(data) {
            // Update the table
            //taken out to reload the page such that new courses are seen.
            //$("#results").html(data);
            //$('#results-table').DataTable();
            location.reload();
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            console.log("Failed to add new entry.")
        }
    });
}
function addCategory(){
    let url = '/addCategory/';
    let cat = $("#categoryNameAdd").val();
    let newData = {};
    newData.category = {"name": cat};
    post(url,newData);
}

function addLO(){
    let url = '/addLO/';
    let lo = $("#loNameAdd").val();
    let category = $("#loCategoryAdd").select2("val");
    let newData = {};
    newData.learningOutcome = {"name": lo};
    newData.category = {"name": category};
    post(url,newData);
}

function addProgram(){
    let url = '/addProgram/';
    let program = $("#programNameAdd").val();
    let course = $("#programCourseAdd").select2("val");
    let newData = {};
    newData.program = {"name": program};
    newData.courseList = course;
    post(url,newData);
}

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

    // Post object to add to table
    post(url, newData);
}

function successCourse() {
    let button = document.getElementById('buttonCourse');
    let name = $("#courseNameAdd").val();
    let code = $("#codeAdd").val();
    let learningO = $("#loAdd option:selected").val();
    let program = $("#programAdd option:selected").val();

    if(code.length <4 || code.length >4 || isNaN(code)==true){
        document.getElementById('codeAdd').style.backgroundColor = "rgb(246,215,218)";
        button.disabled = true;
    } else if(name == "" || name==null || code =="" || code == null  || learningO =="" ||
            learningO == null || program =="" || program == null) {
        document.getElementById('codeAdd').style.backgroundColor = "rgb(255,255,255)";
        button.disabled = true;
    } else{
        document.getElementById('codeAdd').style.backgroundColor = "rgb(255,255,255)";
        button.disabled = false
    }
}

function successCategory() {
    let button = document.getElementById('buttonCategory');
    let name = $("#categoryNameAdd").val();

    if(name == "" || name==null) {
        button.disabled = true;
    } else{
        button.disabled = false
    }
}

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

function successProgram() {
    let button = document.getElementById('buttonProgram');
    let name = $("#programNameAdd").val();
    let course = $("#programCourseAdd option:selected").val();

    if(name == "" || name==null || course == "" || course == null) {
        button.disabled = true;
    } else{
        button.disabled = false
    }
}

