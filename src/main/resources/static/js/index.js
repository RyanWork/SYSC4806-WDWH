/*
* Load jquery DataTables on results table (for sorting)
 */
$(document).ready(function () {
    $('#results-table').DataTable();
});

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

function add() {
    var url = '/add/';
    var name = $("#courseNameAdd").val();
    var code = $("#codeAdd").val();
    var year = $("#yearAdd option:selected").text();
    var category = $("#categoryAdd").val();
    var learningO = $("#loAdd").val();
    var program = $("#programAdd").val();

    // url += name + '/' + code + '/' + year + '/' + category + '/' + learningO + '/' + program;

    var newData = {};
    newData.course = {
        "name": name,
        "code": code,
        "year": year
    };
    newData.category = {"name": category};
    newData.learningOutcome = {"name": learningO};
    newData.program = {"name": program};

    console.log(newData);
    console.log(url);

    console.log(JSON.stringify(newData));

    $.ajax({
        type: "POST",
        url: url,
        data: JSON.stringify(newData),
        dataType : "json",
        contentType: "application/json",
        success: function(response){
            console.log("Successfully added");

        },
        error: function(xhr, ajaxOptions, thrownError){
            console.log(xhr);
            console.log(ajaxOptions);
            console.log(thrownError);
            console.log("Failed to post");
        }
    })

    $("#add").load(encodeURI(url));
}
