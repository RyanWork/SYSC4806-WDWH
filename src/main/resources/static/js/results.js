/**
 * Based on the selected values of Course, Category or Learning Outcomes
 * a CSV files is generated with that data
 */
function exportToCSV() {

    //Get the selected values to export CSV
    var selectedVals = $("#cvsExportVals input:checked").next('label').map(function () {
        return $(this).text();
    }).get();

    var colIndex = [];

    //Checking if any values were selected
    if (selectedVals.length !== 0) {

        //Populate the array as per the selected values
        if (selectedVals.includes("Course")) {
            colIndex.push(0);

        }
        if (selectedVals.includes("Category")) {
            colIndex.push(2);
        }
        if (selectedVals.includes("Learning Outcomes")) {
            colIndex.push(3);
        }

        var csvtitle = populateCsvData(colIndex, 'th');
        var csvContents = populateCsvData(colIndex, 'td');
        var csvData = csvtitle + '\r\n' + csvContents;

        downloadCsvFile(csvData);

    } else {
        alert("Select values to export");
    }

}

/**
 *   Populates data for CSV
 */
function populateCsvData(arrCol, data) {

    var $rows = $("#results-table").find('tr:has(' + data + ')');

    var tmpColDelim = String.fromCharCode(11); // vertical tab character
    var tmpRowDelim = String.fromCharCode(0); // null character

    // actual delimiter characters for CSV format
    var colDelim = '","';
    var rowDelim = '"\r\n"';

    // Grab text from table into CSV formatted string
    csvString = '"' + $rows.map(function (i, row) {
        var $row = $(row);
        $cols = $row.find(data);

        return $cols.map(function (j, col) {

            if (arrCol.includes(j)) {
                var $col = $(col);
                var cellVal = $col.text();
                var text = $.trim(cellVal);
                return text.replace(/"/g, '""'); // escape double quotes
            } else {
                return "";
            }

        }).get().join(tmpColDelim);

    }).get().join(tmpRowDelim)
        .split(tmpRowDelim).join(rowDelim)
        .split(tmpColDelim).join(colDelim) + '"';

    return csvString;
}


/**
 *   Downloads CSV file
 */
function downloadCsvFile(csvData){
    var downloadLink = document.createElement("a");
    var blob = new Blob(["\ufeff", csvData]);
    var url = URL.createObjectURL(blob);
    downloadLink.href = url;
    downloadLink.download = "results.csv";

    document.body.appendChild(downloadLink);
    downloadLink.click();
    document.body.removeChild(downloadLink);
}


