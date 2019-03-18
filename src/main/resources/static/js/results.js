function exportToCSV() {

    var url = "/export/";

    //get the selected values to export CSV
    var selectedVals = $("div.card-body input:checked").next('label').map(function () {
        return $(this).text();
    }).get();

    //checking if any values were selected
    if (selectedVals.length !== 0) {
        var csvExportValsArr = [];

        //populate the array as per the selected values
        if (selectedVals.includes("Course")) {
            csvExportValsArr.push("Course: ");
            populateCsvVals(csvExportValsArr, "td:eq(0)");
            csvExportValsArr.push("$");
        }
        if (selectedVals.includes("Category")) {
            csvExportValsArr.push("Category: ");
            populateCsvVals(csvExportValsArr, "td:eq(2)");
            csvExportValsArr.push("$");
        }
        if (selectedVals.includes("Learning Outcomes")) {
            csvExportValsArr.push("Learning Outcomes: ");
            populateCsvVals(csvExportValsArr, "td:eq(3)");
        }

        var joinedVals = csvExportValsArr.join(" , ");
        url += joinedVals;
    } else {
        url += " ";
    }

    //replaces the specific fragment
    $("#exportCsv").load(encodeURI(url));

}

function populateCsvVals(csvExportValsArr, colNum) {
    $("#courseResults tr").each(function () {
        var cellVal = $(this).find(colNum).text(); //gets the value of a individual cell
        var trimmer = $.trim(cellVal);  //trims whitespace
        if (!(trimmer === "")) {
            csvExportValsArr.push(trimmer); //push the value in the array
        }
        return csvExportValsArr;
    });
}