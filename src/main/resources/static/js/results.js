function exportToCSV() {

    //get the selected values for export CSV
    var selectedVals = $("div.card-body input:checked").next('label').map(function () {
        return $(this).text();
    }).get();

    var csvExportValsArr = [];

    //since getting in coloumn, first one is always empty
    if (selectedVals.includes("Course")) {
        // alert("1");
        //  csvoption += "Course, ";//fix

        populateCsvVals(csvExportValsArr,"td:eq(0)");
    }
    if (selectedVals.includes("Category")) {
        //   csvoption += "Category, ";//fix

        populateCsvVals(csvExportValsArr,"td:eq(2)");
    }
    if (selectedVals.includes("Learning Outcomes")) {
        // alert("3");
        //   csvoption += "Learning Outcomes, ";
        populateCsvVals(csvExportValsArr,"td:eq(3)");
    }

    var joinedVals = csvExportValsArr.join(", ");

    var url = "/export/" + joinedVals;


    $("#exampleModalCenter").load(encodeURI(url));

}

function populateCsvVals(csvExportValsArr, colNum){
    $("#courseResults tr").each(function () {
        var catStr = $(this).find(colNum).text();
        var trimmer = $.trim(catStr);
        csvExportValsArr.push(trimmer);
        return csvExportValsArr;
    });
}