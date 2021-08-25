$(document).ready(function() {
	loadStocks();
});

function loadStocks() {
	var url = 'api/stocks';
	$
			.ajax({
				type : "GET",
				url : url,
				data : null,
				cache : false,
				processData : false,
				contentType : false,
				async : true,
				success : function(response) {
					if ($.fn.DataTable.isDataTable('#stockListTable')) {
						$('#stockListTable').DataTable().destroy();
					}

					var dataSet = response.stocks;

					$('#stockListTable')
							.DataTable(
									{
										"bProcessing" : true,
										"font-size" : "13px",

										"data" : dataSet,
										"columns" : [
												{
													"data" : "stockName",
													"title" : "Stock Name"
												},
												{
													"data" : "stockDesc",
													"title" : "Stock Description"
												},
												{
													"data" : "currentPrice",
													"title" : "Current Price"
												},
												{
													"data" : "lastUpdatedTime",
													"title" : "Last Updated Time"
												},
												{
													"data" : "createdTime",
													"title" : "Created Time"
												},
												{
													"render" : function(data,
															type, full, row) {
														return '<button type="button"  id="button_'
																+ full.id
																+ '" onclick="editStock('
																+ full.id
																+ ');"  class="btn btn-info">Edit</button>';
													}
												},
												{
													"render" : function(data,
															type, full, row) {
														return '<button type="button"  id="button_'
																+ full.id
																+ '" onclick="deleteStock('
																+ full.id
																+ ');" class="btn btn-success">Delete</button>';
													}
												} ],
										"columnDefs" : [ {
											"className" : "dt-center",
											"targets" : "_all"
										} ]
									});
				},
				error : function(e) {
					BootstrapDialog.show({
						title : 'Error Occured! Please contact admin',
						message : '',
						buttons : [ {
							label : 'Okay',
							action : function(dialogItself) {
								dialogItself.close();
							}
						} ]
					});
				}
			});
}

function resetModal() {
	$('#stockIdHiddenText').val('');
	$('#stockNametxt').val('');
	$('#stockDesctxt').val('');
	$('#stockCurrentPriceTxt').val('');
}

function addNewStock() {
	$('#stockModal').modal('show');
	resetModal();
	$('#modalButton').html('Save');
}

function editStock(stockId) {
	$('#stockModal').modal('show');
	resetModal();
	$('#modalButton').html('Update');
	$.ajax({
		url : 'api/stocks/' + parseInt(stockId),
		type : 'GET',
		data : null,
		processData : false,
		async : true,
		timeout : 300000,
		contentType : 'application/json',

		error : function(jqXHR, textStatus, errorThrown) {

			BootstrapDialog.show({
				title : 'Error Occured! Please contact admin',
				message : '',
				buttons : [ {
					label : 'Okay',
					action : function(dialogItself) {
						dialogItself.close();
					}
				} ]
			});
		},
		success : function(response) {
			var respData = response.responseCode;
			if (respData == 1) {
				var stockDetails = response.stock;
				$('#stockIdHiddenText').val(stockDetails.id);
				$('#stockNametxt').val(stockDetails.stockName);
				$('#stockDesctxt').val(stockDetails.stockDesc);
				$('#stockCurrentPriceTxt').val(stockDetails.currentPrice);
			}
		}
	});

}

function saveOrUpdateStock() {
	if ($('#modalButton').html() == 'Save') {
		saveStock();
	} else {
		updateStock();
	}
}

function validateForm() {
	if ($('#stockNametxt').val() == '') {
		bootstrapDialogue('Stock Name is required', 'red');
		$('#stockNametxt').focus();
		return false;
	}
	if ($('#stockDesctxt').val() == '') {
		bootstrapDialogue('Stock Desc is required', 'red');
		$('#stockDesctxt').focus();
		return false;
	}
	if ($('#stockCurrentPriceTxt').val() == '') {
		bootstrapDialogue('Stock Current Price is required', 'red');
		$('#stockCurrentPriceTxt').focus();
		return false;
	}
	return true;
}

function saveStock() {
	if (!validateForm())
		return;

	var formdata = '{"stockName":"' + $('#stockNametxt').val().trim()
			+ '","stockDesc":"' + $('#stockDesctxt').val().trim()
			+ '","currentPrice":"' + $('#stockCurrentPriceTxt').val().trim()
			+ '"}';

	var saveUrl = 'api/stocks';
	$.ajax({
		url : saveUrl,
		type : 'POST',
		data : formdata,
		processData : false,
		async : true,
		timeout : 300000,
		contentType : 'application/json',

		error : function(jqXHR, textStatus, errorThrown) {
			BootstrapDialog.show({
				title : 'Error Occured! Please contact admin',
				message : '',
				buttons : [ {
					label : 'Okay',
					action : function(dialogItself) {
						dialogItself.close();
					}
				} ]
			});
		},
		success : function(respData) {
			var msg = '';
			if (respData.responseCode == 1) {
				msg = 'Stock saved successfully';
				BootstrapDialog.show({
					title : msg,
					message : '',
					buttons : [ {
						label : 'Okay',
						action : function(dialogItself) {
							dialogItself.close();
						}
					} ]
				});
				$('#stockModal').modal('hide');
				loadStocks();
			} else {
				msg = respData.responseDesc;
				BootstrapDialog.show({
					title : msg,
					message : '',
					buttons : [ {
						label : 'Okay',
						action : function(dialogItself) {
							dialogItself.close();
						}
					} ]
				});
			}
		}
	});

}

function updateStock() {
	if (!validateForm())
		return;

	var formdata = '{"stockName":"' + $('#stockNametxt').val().trim()
			+ '","stockDesc":"' + $('#stockDesctxt').val().trim()
			+ '","currentPrice":"' + $('#stockCurrentPriceTxt').val().trim()
			+ '"}';

	var updateUrl = 'api/stocks/'
			+ parseInt($('#stockIdHiddenText').val().trim());
	$.ajax({
		url : updateUrl,
		type : 'PUT',
		data : formdata,
		processData : false,
		async : true,
		timeout : 300000,
		contentType : 'application/json',

		error : function(jqXHR, textStatus, errorThrown) {
			BootstrapDialog.show({
				title : 'Error Occured! Please contact admin',
				message : '',
				buttons : [ {
					label : 'Okay',
					action : function(dialogItself) {
						dialogItself.close();
					}
				} ]
			});
		},
		success : function(respData) {
			var msg = '';
			if (respData.responseCode == 1) {
				msg = 'Stock updated successfully';
				BootstrapDialog.show({
					title : msg,
					message : '',
					buttons : [ {
						label : 'Okay',
						action : function(dialogItself) {
							dialogItself.close();
						}
					} ]
				});
				$('#stockModal').modal('hide');
				loadStocks();
			} else {
				msg = respData.responseDesc;
				BootstrapDialog.show({
					title : msg,
					message : '',
					buttons : [ {
						label : 'Okay',
						action : function(dialogItself) {
							dialogItself.close();
						}
					} ]
				});
			}
		}
	});

}

function deleteStock(stockId) {
	$.ajax({
		url : 'api/stocks/' + parseInt(stockId),
		type : 'DELETE',
		data : null,
		processData : false,
		async : true,
		timeout : 300000,
		contentType : 'application/json',

		error : function(jqXHR, textStatus, errorThrown) {

			BootstrapDialog.show({
				title : 'Error Occured! Please contact admin',
				message : '',
				buttons : [ {
					label : 'Okay',
					action : function(dialogItself) {
						dialogItself.close();
					}
				} ]
			});
		},
		success : function(response) {
			var respData = response.responseCode;
			if (respData == 1) {
				BootstrapDialog.show({
					title : 'Stock Deleted Successfully',
					message : '',
					buttons : [ {
						label : 'Okay',
						action : function(dialogItself) {
							dialogItself.close();
						}
					} ]
				});
				loadStocks();
			}
		}
	});

}

function bootstrapDialogue(msg, color) {
	// loader('hide');
	$('#messageSpan').css('color', color);
	$('#messageSpan').html(msg).show('slow');
	$('#messageSpan').delay(3000).fadeOut();
}
