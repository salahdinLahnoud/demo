$(document).ready( function () {
	 var table = $('#example').DataTable({
			"sAjaxSource": "/lijst",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
			  { "mData": "id"},
		      { "mData": "name" },
		      { "mData": "email" }
			]
	 })
});