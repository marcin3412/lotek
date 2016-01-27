$(document).ready(function(){
if(!no_server_mode){
	////////////////////////////////////////////////////////////////////
	var form = $('#rejestracja').closest('form');
	form.find('input[name=rejestracja]').click(function(event) {
	console.log(form.find('input').serializeArray());
		$.ajax({
			url: 'http://127.0.0.1:8080/trunk/account/register',
			dataType: "jsonp",
			jsonpCallback: 'jsonpCallback',	
			data: form.find('input').serializeArray(),
			success: function(response){
					console.log(response);
			}
		});
		
	});
}
else{
	///////////////////////////////////
	var form = $('#rejestracja').closest('form');
	form.find('input[name=rejestracja]').click(function(event) {
		data = JSON.parse('{"msg":"Dodano uzytkownika do bazy","status":"OK"}');
		//data = JSON.parse('{"msg":"Brak parametrów w zapytaniu.","status":"ERROR"}'');
		//data = JSON.parse('{"msg":"Użytkownik już istnieje.","status":"ERROR"}'');

		//no i tutaj piszesz co ma sie kiedy dziać itd;

		//data.msg  <-- tak odczytujez zmienne
	});	
}
	
});
