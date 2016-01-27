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
		



<form id="rejestracja" class="container">
  <div class="form-group">
    <label class="col-sm-2 control-label">Imię</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="imie" placeholder="Imię">
    </div>
  </div>
  <div class="form-group">
    <label  class="col-sm-2 control-label">Nazwisko</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="nazwisko" placeholder="Nazwisko">
    </div>
  </div>
  <div class="form-group">
    <label  class="col-sm-2 control-label">Email</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="email" placeholder="Email">
    </div>
  </div>
  <div class="form-group">
    <label  class="col-sm-2 control-label">Hasło</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="haslo" placeholder="Hasło">
    </div>
  </div>
  
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <div class="checkbox">
        <label>
          <input type="checkbox"> <a href="#"> Akceptuję Regulamin </a>
        </label>
      </div>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <input type="button" class="btn btn-default" value="Zarejestruj" name="rejestracja" />
    </div>
  </div>
 
</form> 

<?php

include 'footer.php';
?>


</body>
</html>