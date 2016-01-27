<html>
		<!--
    	SEKCJA INCLUDE
    	-->
<head> 	
  <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" >
    <?php
      include 'incl/bootstrap.php';
    ?>
</head>
    	<!--
		KONIEC SEKCJI INCLUDE
		-->

<body>
    	
<?php
    include 'incl/topmenu.php';
?>

 <form id="wylogowanie" class="container">
  <div class="form-group">
    <label class="col-sm-2 control-label">Klucz Aktywacyjny</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="ssid" placeholder="Kod autoryzacyjny sesji">
    </div>
  </div>

  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <br />
      <button name="wyloguj-sie" type="submit" class="btn btn-default">Wyloguj</button>
    </div>
  </div>
</form>   

		<!-----------
			KONIEC SEKCJI CONTENTU
		------------>

<?php

include 'incl/footer.php';

?>
</body>
</html>