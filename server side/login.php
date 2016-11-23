<?php
$phone=$_POST['phone'];  
$pass=$_POST['pass'];   
$con = mysqli_connect("localhost","u704613033_save","cFgQDDpSm3l7I","u704613033_save");
 $sucess="sucess" ;          
 $query="SELECT * FROM `users` WHERE phone ='$phone' and pass='$pass'";
 $result = mysqli_query($con,$query);
 $number_of_row = mysqli_num_rows($result);
 if($number_of_row >= 1) {
 echo json_encode(array("bakr"=>$sucess));
      }
else {
 echo json_encode(array("bakr"=>"failed"));
       }
 ?>
		