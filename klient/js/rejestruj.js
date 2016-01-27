$(document).ready(function(){
var form = $('#rejestracja').closest('form');



	form.find('input[name=rejestracja]').click(function(event) {
	
		$.ajax({

			method: 'GET',
			url: 'http://192.168.0.10:8080/trunk/account/register?',
			data: form.find('input').serializeArray(),
			success: function(response){
				
					console.log(response);
				
			

			},
			error:function(response){

			}
		});
		
	});
});