<?php
$type=$_POST['type'];    
$location_x=$_POST['location_x'];    
$location_y=$_POST['location_y'];    
$city=$_POST['city'];    
$photo=$_POST['photo'];    
$date=$_POST['date'];    
$user_id=$_POST['user_id'];   
  
$con = mysqli_connect("localhost","u704613033_save","cFgQDDpSm3l7I","u704613033_save");
         
         $query="INSERT INTO photos_(type,location_x,location_y,city,photo,date,user_id) VALUES 
                                 ('$type','$location_x','$location_y','$city','$photo','$date','$user_id')";
            if( mysqli_query($con,$query))
                {
                      echo"true";
                }
        else {
         echo"false";
        }
 ?>
			