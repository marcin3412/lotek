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


    	

<form id="logowanie" class="container">
  <div class="form-group">
    <label class="col-sm-2 control-label ">Email</label>
    <div class="col-sm-10">
      <input type="email" class="form-control" name="email" placeholder="Email">
    </div>
  </div>
  <div class="form-group">
    <label  class="col-sm-2 control-label">Hasło</label>
    <div class="col-sm-10">
      <input type="password" class="form-control" name="haslo" placeholder="Hasło">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <div class="checkbox">
        <label>
          <input type="checkbox"> Zapamiętaj mnie
        </label>
      </div>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">Zaloguj</button>
    </div>
  </div>
</form> 

<?php
  include 'incl/footer.php';
?>

</body>
</html>