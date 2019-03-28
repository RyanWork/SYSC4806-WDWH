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

function add() {
    let url = '/add/';
    let name = $("#courseNameAdd").val();
    let code = $("#codeAdd").val();
    let year = $("#yearAdd option:selected").text();
    let category = $("#categoryAdd").val();
    let learningO = $("#loAdd").val();
    let program = $("#programAdd").val();

    // Build the object to post
    let newData = {};
    newData.course = {"name": name, "code": code, "year": year};
    newData.category = {"name": category};
    newData.learningOutcome = {"name": learningO};
    newData.program = {"name": program};

    // Post object to add to table
    $.ajax({
        type: "POST",
        url: url,
        data: JSON.stringify(newData),
        dataType : "text",
        contentType: "application/json",
        success: function(data) {
            // Update the table
            $("#results").html(data);
            $('#results-table').DataTable();
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            console.log("Failed to add new entry.")
        }
    });
}
