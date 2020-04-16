var podatak = "";
var gradovi = "";
function pretvoriGradove(){
	var list = $("#gradMulti").val();
	if(list.length === 0){
		alert("Odaberite bar jedan grad!");
		return;
	}
	$.each(list, function(index, grad) {
		gradovi += grad + ";";
	});
};

$(document).ready(function(){
	$("#gradMulti").tokenize2();
	
	$("form").on('submit',function(e){
		e.preventDefault();
		pretvoriGradove();
		console.log(gradovi);
		console.log("Podaci");
		podatak = $("#podatak option:selected").text();
		$.ajax({
			type : 'GET',
			url : 'rest/tabela/'+ gradovi,
			contentType : 'application/json',
			success : function(items){
				console.log("tabela");
				kreirajTabelu(items);
			
				
			},
			error: function(xhr, status, error) {
				console.log("xhr = " + xhr + " status = " + status + " error = " + error);
				alert(xhr.responseText);
			}
		});
		$.ajax({
			type : 'GET',
			url : 'rest/grafikon/'+ gradovi + "/" + $("#podatak").val()  + "/" + $("#interval").val(),
			contentType : 'application/json',
			success : function(items){
				console.log("tabela");
				kreirajGrafikon(items);
			
				
			},
			error: function(xhr, status, error) {
				console.log("xhr = " + xhr + " status = " + status + " error = " + error);
				alert(xhr.responseText);
			}
		});
		gradovi = "";
	});
});


function kreirajGrafikon(data) {
	var list = data.gradovi == null ? [] : (data.gradovi instanceof Array ? data.gradovi : [ data.gradovi ]);
	var podaci = [];
	console.log(list);
	$.each(list, function(index, graf) {
		var tacke = [];
		console.log(graf);
		var listaVrednosti = graf.merenja == null ? [] : (graf.merenja instanceof Array ? graf.merenja : [ graf.merenja ]);
		var listaDatuma = graf.datumi == null ? [] : ( graf.datumi instanceof Array ?  graf.datumi : [  graf.datumi ]);
		for ( var i = 0, l = listaVrednosti.length; i < l; i++ ) {
			console.log( listaDatuma[i]);
			console.log( listaVrednosti[i]);
		    var obj = { label: listaDatuma[i], y: parseFloat(listaVrednosti[i])};
		    console.log(obj);
		    tacke.push(obj);
		}
		console.log(tacke);
		podaci.push({
			type: "line", //try changing to column, area
			toolTipContent: "{label}: {y} mm",
			showInLegend: true, 
	        name: "series" + index,
	        legendText: graf.grad,
			dataPoints: tacke
		});
	});
	$(".chartContainer").CanvasJSChart({
		title: {
			text: "Dijagram" 
		},
		axisY: {
			title: podatak,
			includeZero: false
		},
		axisX: {
			interval: 1
		},
		data: podaci
	});
}
function kreirajTabelu(data) {
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	var $tbody = $('#tabela').children('tbody') 
	$tbody.empty();
	$.each(list, function(index, grad) {
        var tr = $('<tr></tr>');
        tr.append('<td>' + grad.ime + '</td>' +
                '<td>' + grad.datum + '</td>' + 
                '<td>' + grad.temperatura + '</td>' + 
                '<td>' + grad.tempMin + '</td>' +
                '<td>' + grad.tempMax + '</td>' +
                '<td>' + grad.pritisak + '</td>' + 
                '<td>' + grad.vidljivost + '</td>' + 
                '<td>' + grad.vlaznost + '</td>');
        $('#tabela').children('tbody').append(tr);
	});
}