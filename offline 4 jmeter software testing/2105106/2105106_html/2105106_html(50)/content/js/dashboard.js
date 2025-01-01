/*
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
var showControllersOnly = false;
var seriesFilter = "";
var filtersOnlySampleSeries = true;

/*
 * Add header in statistics table to group metrics by category
 * format
 *
 */
function summaryTableHeader(header) {
    var newRow = header.insertRow(-1);
    newRow.className = "tablesorter-no-sort";
    var cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Requests";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 3;
    cell.innerHTML = "Executions";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 7;
    cell.innerHTML = "Response Times (ms)";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Throughput";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 2;
    cell.innerHTML = "Network (KB/sec)";
    newRow.appendChild(cell);
}

/*
 * Populates the table identified by id parameter with the specified data and
 * format
 *
 */
function createTable(table, info, formatter, defaultSorts, seriesIndex, headerCreator) {
    var tableRef = table[0];

    // Create header and populate it with data.titles array
    var header = tableRef.createTHead();

    // Call callback is available
    if(headerCreator) {
        headerCreator(header);
    }

    var newRow = header.insertRow(-1);
    for (var index = 0; index < info.titles.length; index++) {
        var cell = document.createElement('th');
        cell.innerHTML = info.titles[index];
        newRow.appendChild(cell);
    }

    var tBody;

    // Create overall body if defined
    if(info.overall){
        tBody = document.createElement('tbody');
        tBody.className = "tablesorter-no-sort";
        tableRef.appendChild(tBody);
        var newRow = tBody.insertRow(-1);
        var data = info.overall.data;
        for(var index=0;index < data.length; index++){
            var cell = newRow.insertCell(-1);
            cell.innerHTML = formatter ? formatter(index, data[index]): data[index];
        }
    }

    // Create regular body
    tBody = document.createElement('tbody');
    tableRef.appendChild(tBody);

    var regexp;
    if(seriesFilter) {
        regexp = new RegExp(seriesFilter, 'i');
    }
    // Populate body with data.items array
    for(var index=0; index < info.items.length; index++){
        var item = info.items[index];
        if((!regexp || filtersOnlySampleSeries && !info.supportsControllersDiscrimination || regexp.test(item.data[seriesIndex]))
                &&
                (!showControllersOnly || !info.supportsControllersDiscrimination || item.isController)){
            if(item.data.length > 0) {
                var newRow = tBody.insertRow(-1);
                for(var col=0; col < item.data.length; col++){
                    var cell = newRow.insertCell(-1);
                    cell.innerHTML = formatter ? formatter(col, item.data[col]) : item.data[col];
                }
            }
        }
    }

    // Add support of columns sort
    table.tablesorter({sortList : defaultSorts});
}

$(document).ready(function() {

    // Customize table sorter default options
    $.extend( $.tablesorter.defaults, {
        theme: 'blue',
        cssInfoBlock: "tablesorter-no-sort",
        widthFixed: true,
        widgets: ['zebra']
    });

    var data = {"OkPercent": 84.9570200573066, "KoPercent": 15.04297994269341};
    var dataset = [
        {
            "label" : "FAIL",
            "data" : data.KoPercent,
            "color" : "#FF6347"
        },
        {
            "label" : "PASS",
            "data" : data.OkPercent,
            "color" : "#9ACD32"
        }];
    $.plot($("#flot-requests-summary"), dataset, {
        series : {
            pie : {
                show : true,
                radius : 1,
                label : {
                    show : true,
                    radius : 3 / 4,
                    formatter : function(label, series) {
                        return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'
                            + label
                            + '<br/>'
                            + Math.round10(series.percent, -2)
                            + '%</div>';
                    },
                    background : {
                        opacity : 0.5,
                        color : '#000'
                    }
                }
            }
        },
        legend : {
            show : true
        }
    });

    // Creates APDEX table
    createTable($("#apdexTable"), {"supportsControllersDiscrimination": true, "overall": {"data": [0.6862464183381088, 500, 1500, "Total"], "isController": false}, "titles": ["Apdex", "T (Toleration threshold)", "F (Frustration threshold)", "Label"], "items": [{"data": [0.64, 500, 1500, "ryans/storage/products/small/logitech-c270-webcam-11686572193.webp-244"], "isController": false}, {"data": [0.7, 500, 1500, "startech/gtag/js-180"], "isController": false}, {"data": [0.86, 500, 1500, "reddit/shreddit/en-US/post-d6ddae05.js-570"], "isController": false}, {"data": [0.62, 500, 1500, "ryans/storage/products/small/revenger-lite-power-350w-non-modular-80-plus-11708857908.webp-246"], "isController": false}, {"data": [0.72, 500, 1500, "ryans/beacon.min.js/vcd15cbe7772f49c399c6a5babf22c1241717689176015-247"], "isController": false}, {"data": [1.0, 500, 1500, "startech/en_US/fbevents.js-181"], "isController": false}, {"data": [0.66, 500, 1500, "ryans/storage/products/small/dahua-dh-hac-t1a21p-28mm-2mp-dome-cc-11706171450.webp-245"], "isController": false}, {"data": [0.88, 500, 1500, "reddit/submit/firefox-desktop/newtab/1/276f9fa9-2b9a-4378-a19b-709f7efda6a6-566"], "isController": false}, {"data": [0.46938775510204084, 500, 1500, "reddit/-564-0"], "isController": false}, {"data": [0.03, 500, 1500, "reddit/-564"], "isController": false}, {"data": [0.1326530612244898, 500, 1500, "reddit/-564-1"], "isController": false}, {"data": [0.98, 500, 1500, "startech/-183"], "isController": false}, {"data": [0.94, 500, 1500, "reddit/submit/activity-stream/sessions/1/27ad386e-683d-455d-a7e0-223a2a8dac7c-565"], "isController": false}, {"data": [0.96, 500, 1500, "startech/update/3/SystemAddons/106.0.1/20221019185550/WINNT_x86_64-msvc-x64/en-US/release/Windows_NT%2010.0.0.0.26100.2605%20(x64)/default/default/update.xml-182"], "isController": false}]}, function(index, item){
        switch(index){
            case 0:
                item = item.toFixed(3);
                break;
            case 1:
            case 2:
                item = formatDuration(item);
                break;
        }
        return item;
    }, [[0, 0]], 3);

    // Create statistics table
    createTable($("#statisticsTable"), {"supportsControllersDiscrimination": true, "overall": {"data": ["Total", 698, 105, 15.04297994269341, 5956.525787965615, 13, 96631, 200.0, 20629.3, 39982.65, 77169.04, 4.348151101365493, 141.90749262784374, 2.6864107235186387], "isController": false}, "titles": ["Label", "#Samples", "FAIL", "Error %", "Average", "Min", "Max", "Median", "90th pct", "95th pct", "99th pct", "Transactions/s", "Received", "Sent"], "items": [{"data": ["ryans/storage/products/small/logitech-c270-webcam-11686572193.webp-244", 50, 18, 36.0, 122.48, 27, 564, 52.0, 244.89999999999998, 356.2499999999991, 564.0, 0.5114618602890783, 3.026036125829847, 0.21527349783651634], "isController": false}, {"data": ["startech/gtag/js-180", 50, 9, 18.0, 515.28, 446, 851, 486.5, 593.3, 721.3999999999996, 851.0, 0.5077379259921199, 62.3061869135627, 0.19288091133880336], "isController": false}, {"data": ["reddit/shreddit/en-US/post-d6ddae05.js-570", 50, 7, 14.0, 181.84, 167, 218, 177.5, 206.0, 208.79999999999998, 218.0, 0.35985461873403146, 0.5362677228399727, 0.14865088254345246], "isController": false}, {"data": ["ryans/storage/products/small/revenger-lite-power-350w-non-modular-80-plus-11708857908.webp-246", 50, 19, 38.0, 41.82, 15, 93, 23.0, 82.69999999999999, 87.79999999999998, 93.0, 0.5109600940166573, 10.8222445678555, 0.22703793239997955], "isController": false}, {"data": ["ryans/beacon.min.js/vcd15cbe7772f49c399c6a5babf22c1241717689176015-247", 50, 14, 28.0, 542.9000000000001, 89, 6254, 161.5, 1120.6, 2423.0999999999995, 6254.0, 0.5104488887527693, 3.6557910745459554, 0.209363802027503], "isController": false}, {"data": ["startech/en_US/fbevents.js-181", 50, 0, 0.0, 74.44000000000001, 64, 114, 73.0, 84.69999999999999, 99.14999999999998, 114.0, 0.5101364104761613, 32.60259879429259, 0.18881025348678235], "isController": false}, {"data": ["ryans/storage/products/small/dahua-dh-hac-t1a21p-28mm-2mp-dome-cc-11706171450.webp-245", 50, 17, 34.0, 40.720000000000006, 13, 111, 20.5, 84.69999999999999, 89.0, 111.0, 0.5109966478619901, 2.5567896923033686, 0.22306201327569292], "isController": false}, {"data": ["reddit/submit/firefox-desktop/newtab/1/276f9fa9-2b9a-4378-a19b-709f7efda6a6-566", 50, 6, 12.0, 267.68, 219, 302, 272.0, 292.0, 299.25, 302.0, 0.3596320245125188, 0.2910771698398199, 0.6146055106415116], "isController": false}, {"data": ["reddit/-564-0", 49, 0, 0.0, 15184.673469387759, 167, 60092, 19451.0, 38781.0, 48428.0, 60092.0, 0.358187134502924, 0.5588036252741228, 0.1560072870979532], "isController": false}, {"data": ["reddit/-564", 50, 6, 12.0, 41072.279999999984, 1030, 96631, 39942.0, 77317.5, 87602.79999999993, 96631.0, 0.31857888331729817, 30.86033820334253, 0.28853789017948733], "isController": false}, {"data": ["reddit/-564-1", 49, 3, 6.122448979591836, 25150.36734693878, 848, 77173, 20468.0, 59744.0, 77153.0, 77173.0, 0.31262959772865023, 30.393695469263402, 0.15276332264650525], "isController": false}, {"data": ["startech/-183", 50, 1, 2.0, 196.54, 108, 1237, 154.5, 249.9, 276.54999999999984, 1237.0, 0.5107252298263535, 16.587717058222676, 0.2249385533707865], "isController": false}, {"data": ["reddit/submit/activity-stream/sessions/1/27ad386e-683d-455d-a7e0-223a2a8dac7c-565", 50, 3, 6.0, 388.47999999999996, 342, 737, 384.5, 413.8, 421.59999999999997, 737.0, 0.3595725401642527, 0.28949100933809885, 0.4645649127317445], "isController": false}, {"data": ["startech/update/3/SystemAddons/106.0.1/20221019185550/WINNT_x86_64-msvc-x64/en-US/release/Windows_NT%2010.0.0.0.26100.2605%20(x64)/default/default/update.xml-182", 50, 2, 4.0, 180.29999999999998, 165, 445, 171.0, 184.0, 231.04999999999978, 445.0, 0.5090664739001619, 0.2348566246092915, 0.2530418312648266], "isController": false}]}, function(index, item){
        switch(index){
            // Errors pct
            case 3:
                item = item.toFixed(2) + '%';
                break;
            // Mean
            case 4:
            // Mean
            case 7:
            // Median
            case 8:
            // Percentile 1
            case 9:
            // Percentile 2
            case 10:
            // Percentile 3
            case 11:
            // Throughput
            case 12:
            // Kbytes/s
            case 13:
            // Sent Kbytes/s
                item = item.toFixed(2);
                break;
        }
        return item;
    }, [[0, 0]], 0, summaryTableHeader);

    // Create error table
    createTable($("#errorsTable"), {"supportsControllersDiscrimination": false, "titles": ["Type of error", "Number of errors", "% in errors", "% in all samples"], "items": [{"data": ["The operation lasted too long: It took 2,361 milliseconds, but should not have lasted longer than 395 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 89 milliseconds, but should not have lasted longer than 70 milliseconds.", 2, 1.9047619047619047, 0.28653295128939826], "isController": false}, {"data": ["The operation lasted too long: It took 469 milliseconds, but should not have lasted longer than 205 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 83 milliseconds, but should not have lasted longer than 70 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 74 milliseconds, but should not have lasted longer than 70 milliseconds.", 2, 1.9047619047619047, 0.28653295128939826], "isController": false}, {"data": ["The operation lasted too long: It took 594 milliseconds, but should not have lasted longer than 565 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 225 milliseconds, but should not have lasted longer than 205 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 1,237 milliseconds, but should not have lasted longer than 367 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 1,097 milliseconds, but should not have lasted longer than 395 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 246 milliseconds, but should not have lasted longer than 205 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 73 milliseconds, but should not have lasted longer than 70 milliseconds.", 5, 4.761904761904762, 0.7163323782234957], "isController": false}, {"data": ["The operation lasted too long: It took 79 milliseconds, but should not have lasted longer than 70 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 82 milliseconds, but should not have lasted longer than 70 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 1,105 milliseconds, but should not have lasted longer than 395 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 579 milliseconds, but should not have lasted longer than 565 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 574 milliseconds, but should not have lasted longer than 565 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 226 milliseconds, but should not have lasted longer than 205 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 445 milliseconds, but should not have lasted longer than 215 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 958 milliseconds, but should not have lasted longer than 395 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 737 milliseconds, but should not have lasted longer than 415 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 908 milliseconds, but should not have lasted longer than 395 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 79,218 milliseconds, but should not have lasted longer than 62,588 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 1,122 milliseconds, but should not have lasted longer than 395 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 75 milliseconds, but should not have lasted longer than 70 milliseconds.", 3, 2.857142857142857, 0.4297994269340974], "isController": false}, {"data": ["The operation lasted too long: It took 93 milliseconds, but should not have lasted longer than 70 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 292 milliseconds, but should not have lasted longer than 290 milliseconds.", 2, 1.9047619047619047, 0.28653295128939826], "isController": false}, {"data": ["The operation lasted too long: It took 680 milliseconds, but should not have lasted longer than 565 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 217 milliseconds, but should not have lasted longer than 205 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 211 milliseconds, but should not have lasted longer than 205 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 205 milliseconds, but should not have lasted longer than 200 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 111 milliseconds, but should not have lasted longer than 70 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 221 milliseconds, but should not have lasted longer than 205 milliseconds.", 3, 2.857142857142857, 0.4297994269340974], "isController": false}, {"data": ["The operation lasted too long: It took 224 milliseconds, but should not have lasted longer than 205 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 409 milliseconds, but should not have lasted longer than 395 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 418 milliseconds, but should not have lasted longer than 415 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 80 milliseconds, but should not have lasted longer than 70 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 599 milliseconds, but should not have lasted longer than 395 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 587 milliseconds, but should not have lasted longer than 565 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 293 milliseconds, but should not have lasted longer than 290 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 772 milliseconds, but should not have lasted longer than 565 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 564 milliseconds, but should not have lasted longer than 205 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 264 milliseconds, but should not have lasted longer than 205 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 617 milliseconds, but should not have lasted longer than 565 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 207 milliseconds, but should not have lasted longer than 200 milliseconds.", 2, 1.9047619047619047, 0.28653295128939826], "isController": false}, {"data": ["The operation lasted too long: It took 211 milliseconds, but should not have lasted longer than 200 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 6,254 milliseconds, but should not have lasted longer than 395 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 1,243 milliseconds, but should not have lasted longer than 395 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 77 milliseconds, but should not have lasted longer than 70 milliseconds.", 3, 2.857142857142857, 0.4297994269340974], "isController": false}, {"data": ["The operation lasted too long: It took 570 milliseconds, but should not have lasted longer than 565 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 249 milliseconds, but should not have lasted longer than 205 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 86 milliseconds, but should not have lasted longer than 70 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 218 milliseconds, but should not have lasted longer than 200 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 302 milliseconds, but should not have lasted longer than 290 milliseconds.", 2, 1.9047619047619047, 0.28653295128939826], "isController": false}, {"data": ["The operation lasted too long: It took 208 milliseconds, but should not have lasted longer than 205 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 2,499 milliseconds, but should not have lasted longer than 395 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 1,099 milliseconds, but should not have lasted longer than 395 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 223 milliseconds, but should not have lasted longer than 205 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 76 milliseconds, but should not have lasted longer than 70 milliseconds.", 3, 2.857142857142857, 0.4297994269340974], "isController": false}, {"data": ["The operation lasted too long: It took 1,108 milliseconds, but should not have lasted longer than 395 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 207 milliseconds, but should not have lasted longer than 205 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 85 milliseconds, but should not have lasted longer than 70 milliseconds.", 3, 2.857142857142857, 0.4297994269340974], "isController": false}, {"data": ["The operation lasted too long: It took 210 milliseconds, but should not have lasted longer than 205 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 80,239 milliseconds, but should not have lasted longer than 62,588 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 206 milliseconds, but should not have lasted longer than 200 milliseconds.", 2, 1.9047619047619047, 0.28653295128939826], "isController": false}, {"data": ["The operation lasted too long: It took 90 milliseconds, but should not have lasted longer than 70 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["Non HTTP response code: org.apache.http.conn.ConnectTimeoutException/Non HTTP response message: Connect to www.reddit.com:443 [www.reddit.com/151.101.1.140, www.reddit.com/151.101.193.140, www.reddit.com/151.101.129.140, www.reddit.com/151.101.65.140] failed: Read timed out", 2, 1.9047619047619047, 0.28653295128939826], "isController": false}, {"data": ["Non HTTP response code: org.apache.http.conn.ConnectTimeoutException/Non HTTP response message: Connect to www.reddit.com:443 [www.reddit.com/151.101.129.140, www.reddit.com/151.101.1.140, www.reddit.com/151.101.193.140, www.reddit.com/151.101.65.140] failed: Read timed out", 5, 4.761904761904762, 0.7163323782234957], "isController": false}, {"data": ["The operation lasted too long: It took 72 milliseconds, but should not have lasted longer than 70 milliseconds.", 3, 2.857142857142857, 0.4297994269340974], "isController": false}, {"data": ["The operation lasted too long: It took 851 milliseconds, but should not have lasted longer than 565 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 235 milliseconds, but should not have lasted longer than 205 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 297 milliseconds, but should not have lasted longer than 290 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 78 milliseconds, but should not have lasted longer than 70 milliseconds.", 3, 2.857142857142857, 0.4297994269340974], "isController": false}, {"data": ["The operation lasted too long: It took 639 milliseconds, but should not have lasted longer than 395 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 426 milliseconds, but should not have lasted longer than 415 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 71 milliseconds, but should not have lasted longer than 70 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}, {"data": ["The operation lasted too long: It took 258 milliseconds, but should not have lasted longer than 215 milliseconds.", 1, 0.9523809523809523, 0.14326647564469913], "isController": false}]}, function(index, item){
        switch(index){
            case 2:
            case 3:
                item = item.toFixed(2) + '%';
                break;
        }
        return item;
    }, [[1, 1]]);

        // Create top5 errors by sampler
    createTable($("#top5ErrorsBySamplerTable"), {"supportsControllersDiscrimination": false, "overall": {"data": ["Total", 698, 105, "The operation lasted too long: It took 73 milliseconds, but should not have lasted longer than 70 milliseconds.", 5, "Non HTTP response code: org.apache.http.conn.ConnectTimeoutException/Non HTTP response message: Connect to www.reddit.com:443 [www.reddit.com/151.101.129.140, www.reddit.com/151.101.1.140, www.reddit.com/151.101.193.140, www.reddit.com/151.101.65.140] failed: Read timed out", 5, "The operation lasted too long: It took 75 milliseconds, but should not have lasted longer than 70 milliseconds.", 3, "The operation lasted too long: It took 221 milliseconds, but should not have lasted longer than 205 milliseconds.", 3, "The operation lasted too long: It took 77 milliseconds, but should not have lasted longer than 70 milliseconds.", 3], "isController": false}, "titles": ["Sample", "#Samples", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors"], "items": [{"data": ["ryans/storage/products/small/logitech-c270-webcam-11686572193.webp-244", 50, 18, "The operation lasted too long: It took 221 milliseconds, but should not have lasted longer than 205 milliseconds.", 3, "The operation lasted too long: It took 210 milliseconds, but should not have lasted longer than 205 milliseconds.", 1, "The operation lasted too long: It took 564 milliseconds, but should not have lasted longer than 205 milliseconds.", 1, "The operation lasted too long: It took 469 milliseconds, but should not have lasted longer than 205 milliseconds.", 1, "The operation lasted too long: It took 264 milliseconds, but should not have lasted longer than 205 milliseconds.", 1], "isController": false}, {"data": ["startech/gtag/js-180", 50, 9, "The operation lasted too long: It took 587 milliseconds, but should not have lasted longer than 565 milliseconds.", 1, "The operation lasted too long: It took 851 milliseconds, but should not have lasted longer than 565 milliseconds.", 1, "The operation lasted too long: It took 772 milliseconds, but should not have lasted longer than 565 milliseconds.", 1, "The operation lasted too long: It took 579 milliseconds, but should not have lasted longer than 565 milliseconds.", 1, "The operation lasted too long: It took 574 milliseconds, but should not have lasted longer than 565 milliseconds.", 1], "isController": false}, {"data": ["reddit/shreddit/en-US/post-d6ddae05.js-570", 50, 7, "The operation lasted too long: It took 207 milliseconds, but should not have lasted longer than 200 milliseconds.", 2, "The operation lasted too long: It took 206 milliseconds, but should not have lasted longer than 200 milliseconds.", 2, "The operation lasted too long: It took 205 milliseconds, but should not have lasted longer than 200 milliseconds.", 1, "The operation lasted too long: It took 211 milliseconds, but should not have lasted longer than 200 milliseconds.", 1, "The operation lasted too long: It took 218 milliseconds, but should not have lasted longer than 200 milliseconds.", 1], "isController": false}, {"data": ["ryans/storage/products/small/revenger-lite-power-350w-non-modular-80-plus-11708857908.webp-246", 50, 19, "The operation lasted too long: It took 73 milliseconds, but should not have lasted longer than 70 milliseconds.", 3, "The operation lasted too long: It took 78 milliseconds, but should not have lasted longer than 70 milliseconds.", 3, "The operation lasted too long: It took 72 milliseconds, but should not have lasted longer than 70 milliseconds.", 2, "The operation lasted too long: It took 83 milliseconds, but should not have lasted longer than 70 milliseconds.", 1, "The operation lasted too long: It took 75 milliseconds, but should not have lasted longer than 70 milliseconds.", 1], "isController": false}, {"data": ["ryans/beacon.min.js/vcd15cbe7772f49c399c6a5babf22c1241717689176015-247", 50, 14, "The operation lasted too long: It took 2,361 milliseconds, but should not have lasted longer than 395 milliseconds.", 1, "The operation lasted too long: It took 1,122 milliseconds, but should not have lasted longer than 395 milliseconds.", 1, "The operation lasted too long: It took 6,254 milliseconds, but should not have lasted longer than 395 milliseconds.", 1, "The operation lasted too long: It took 1,243 milliseconds, but should not have lasted longer than 395 milliseconds.", 1, "The operation lasted too long: It took 1,097 milliseconds, but should not have lasted longer than 395 milliseconds.", 1], "isController": false}, {"data": [], "isController": false}, {"data": ["ryans/storage/products/small/dahua-dh-hac-t1a21p-28mm-2mp-dome-cc-11706171450.webp-245", 50, 17, "The operation lasted too long: It took 73 milliseconds, but should not have lasted longer than 70 milliseconds.", 2, "The operation lasted too long: It took 76 milliseconds, but should not have lasted longer than 70 milliseconds.", 2, "The operation lasted too long: It took 89 milliseconds, but should not have lasted longer than 70 milliseconds.", 2, "The operation lasted too long: It took 74 milliseconds, but should not have lasted longer than 70 milliseconds.", 2, "The operation lasted too long: It took 75 milliseconds, but should not have lasted longer than 70 milliseconds.", 2], "isController": false}, {"data": ["reddit/submit/firefox-desktop/newtab/1/276f9fa9-2b9a-4378-a19b-709f7efda6a6-566", 50, 6, "The operation lasted too long: It took 292 milliseconds, but should not have lasted longer than 290 milliseconds.", 2, "The operation lasted too long: It took 302 milliseconds, but should not have lasted longer than 290 milliseconds.", 2, "The operation lasted too long: It took 293 milliseconds, but should not have lasted longer than 290 milliseconds.", 1, "The operation lasted too long: It took 297 milliseconds, but should not have lasted longer than 290 milliseconds.", 1, "", ""], "isController": false}, {"data": [], "isController": false}, {"data": ["reddit/-564", 50, 6, "Non HTTP response code: org.apache.http.conn.ConnectTimeoutException/Non HTTP response message: Connect to www.reddit.com:443 [www.reddit.com/151.101.129.140, www.reddit.com/151.101.1.140, www.reddit.com/151.101.193.140, www.reddit.com/151.101.65.140] failed: Read timed out", 3, "The operation lasted too long: It took 80,239 milliseconds, but should not have lasted longer than 62,588 milliseconds.", 1, "Non HTTP response code: org.apache.http.conn.ConnectTimeoutException/Non HTTP response message: Connect to www.reddit.com:443 [www.reddit.com/151.101.1.140, www.reddit.com/151.101.193.140, www.reddit.com/151.101.129.140, www.reddit.com/151.101.65.140] failed: Read timed out", 1, "The operation lasted too long: It took 79,218 milliseconds, but should not have lasted longer than 62,588 milliseconds.", 1, "", ""], "isController": false}, {"data": ["reddit/-564-1", 49, 3, "Non HTTP response code: org.apache.http.conn.ConnectTimeoutException/Non HTTP response message: Connect to www.reddit.com:443 [www.reddit.com/151.101.129.140, www.reddit.com/151.101.1.140, www.reddit.com/151.101.193.140, www.reddit.com/151.101.65.140] failed: Read timed out", 2, "Non HTTP response code: org.apache.http.conn.ConnectTimeoutException/Non HTTP response message: Connect to www.reddit.com:443 [www.reddit.com/151.101.1.140, www.reddit.com/151.101.193.140, www.reddit.com/151.101.129.140, www.reddit.com/151.101.65.140] failed: Read timed out", 1, "", "", "", "", "", ""], "isController": false}, {"data": ["startech/-183", 50, 1, "The operation lasted too long: It took 1,237 milliseconds, but should not have lasted longer than 367 milliseconds.", 1, "", "", "", "", "", "", "", ""], "isController": false}, {"data": ["reddit/submit/activity-stream/sessions/1/27ad386e-683d-455d-a7e0-223a2a8dac7c-565", 50, 3, "The operation lasted too long: It took 426 milliseconds, but should not have lasted longer than 415 milliseconds.", 1, "The operation lasted too long: It took 418 milliseconds, but should not have lasted longer than 415 milliseconds.", 1, "The operation lasted too long: It took 737 milliseconds, but should not have lasted longer than 415 milliseconds.", 1, "", "", "", ""], "isController": false}, {"data": ["startech/update/3/SystemAddons/106.0.1/20221019185550/WINNT_x86_64-msvc-x64/en-US/release/Windows_NT%2010.0.0.0.26100.2605%20(x64)/default/default/update.xml-182", 50, 2, "The operation lasted too long: It took 445 milliseconds, but should not have lasted longer than 215 milliseconds.", 1, "The operation lasted too long: It took 258 milliseconds, but should not have lasted longer than 215 milliseconds.", 1, "", "", "", "", "", ""], "isController": false}]}, function(index, item){
        return item;
    }, [[0, 0]], 0);

});
