<html>

<head>  
  <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" >
    <?php
      include 'incl/bootstrap.php';
    ?>
</head>


<body>
    	
  <?php
    include 'incl/topmenu.php';
  ?>
		


		


  <form id="potwierdzenie" class="container">
  <div class="form-group">
    <label class="col-sm-2 control-label">Klucz Aktywacyjny</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="confirm_cod" placeholder="Kod potwierdzający">
    </div>
  </div>

  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <br />
      <button type="submit" class="btn btn-default">Aktywuj</button>
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