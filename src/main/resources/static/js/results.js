function exportToCSV() {

    //get the selected values for export CSV
    var selectedVals = $("div.card-body input:checked").next('label').map(function () {
        return $(this).text();
    }).get();

    var csvExportValsArr = [];

    //since getting in coloumn, first one is always empty
    if (selectedVals.includes("Course")) {
        csvExportValsArr.push("Course: ");
        populateCsvVals(csvExportValsArr,"td:eq(0)");
        csvExportValsArr.push("$");
    }
    if (selectedVals.includes("Category")) {
        csvExportValsArr.push("Category: ");
        populateCsvVals(csvExportValsArr,"td:eq(2)");
        csvExportValsArr.push("$");
    }
    if (selectedVals.includes("Learning Outcomes")) {
        csvExportValsArr.push("Learning Outcomes: ");
        populateCsvVals(csvExportValsArr,"td:eq(3)");
        csvExportValsArr.push("$");
    }

    var joinedVals = csvExportValsArr.join(", ");
    var url = "/export/" + joinedVals;

    $("#exampleModalCenter").load(encodeURI(url));

}

function populateCsvVals(csvExportValsArr, colNum){
    $("#courseResults tr").each(function () {
        var cellVal = $(this).find(colNum).text(); //gets the value of a individual cell
        var trimmer = $.trim(cellVal);  //trims whitespace
        if(!(trimmer === "")){
            csvExportValsArr.push(trimmer); //push the value in the array
        }
        return csvExportValsArr;
    });
}