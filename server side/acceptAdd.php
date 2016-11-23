<?php
$userId1=$_POST['user_id1'];    
$userId2=$_POST['user_id12'];

 $con = mysqli_connect("localhost","u704613033_save","cFgQDDpSm3l7I","u704613033_save");
         
         $query="INSERT INTO friends(id_user,id_user2) VALUES 
                                 ('$userId1','$userId2')";
            if( mysqli_query($con,$query))
                {
                       echo "true";
                }
        else {
         echo"false";
        }
 ?>
			