$(document).ready(function () {
    $('#example').DataTable();
});

// $('#example').DataTable();

//
// $('#example').DataTable({
//     "processing": true,
//     "serverSide": true,
//     "ajax": {
//         "url": "/employees",
//         "type": "POST",
//         "dataType": "json",
//         "contentType": "application/json",
//         "data": function (d) {
//             return JSON.stringify(d);
//         }
//     },
//     "columns": [
//         {"data": "name", "width": "20%"},
//         {"data": "position","width": "20%"},
//         {"data": "office", "width": "20%"},
//         {"data": "start_date", "width": "20%"},
//         {"data": "salary", "width": "20%"}
//     ]
// });
//
// $('#exampleArray').DataTable({
//     "processing": true,
//     "serverSide": true,
//     "ajax": {
//         "url": "/employees/array",
//         "type": "POST",
//         "dataType": "json",
//         "contentType": "application/json",
//         "data": function (d) {
//             return JSON.stringify(d);
//         }
//     }
// });

// alert("Hello world!");