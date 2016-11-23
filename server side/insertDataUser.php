<?php
$first_name=$_POST['first_name'];    
$last_name=$_POST['last_name'];  
$pass=$_POST['pass'];  
$phone=$_POST['phone'];    
$photo=$_POST['photo'];    
$country=$_POST['country'];    
$goverment=$_POST['goverment'];    
$city=$_POST['city'];    
$location_x=$_POST['location_x'];    
$location_y=$_POST['location_y'];    
$token=$_POST['token'];    
 $con = mysqli_connect("localhost","u704613033_save","cFgQDDpSm3l7I","u704613033_save");
         
         $query="INSERT INTO users(first_name,last_name,pass,phone,photo,country,goverment,city,location_x,location_y,token) VALUES 
                                 ('$first_name','$last_name','$pass','$phone','$photo','$country','$goverment','$city','$location_x','$location_y','$token')";
            if( mysqli_query($con,$query))
                {
                       echo "true";
                }
        else {
         echo"false";
        }
 ?>
			