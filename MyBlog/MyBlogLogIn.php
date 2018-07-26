<?php 

	require_once 'MyBLogView.php';
	
?>
	<main style = 'float:left; width :80%; position: absolute; top: 100px' >
	<form method="post" action = "" width = 80% onsubmit = "validation()">
			<table style = "border:2px solid black">
				<tbody>
					<tr><td><label>email adress</label></td><td><input type="text" name = "userEmail" size = "20" pattern = "[A-Za-z0-9.]+[^()$<>]@[A-Za-z]+[^()$<>].[A-Za-z]{2,3}" title = "please fill all the fields" ></td></tr>
					<tr><td><label>password</label></td><td><input type="password" name = "userPassword" size = "10" pattern = "[A-Za-z]+[^()$<>]" title = "please fill all the fields" ></td></tr>
					<tr><td><a href="MyBlogUserRegistration.php">Registrate</a></td><td><input type="submit" name="LogInSubmit" value = "send"></td></tr>
				</tbody>
			</table>
	</form>
	</main>
	
<?php
	if(isset($_POST['LogInSubmit'])) {
	if (!empty($_POST['userEmail']) & !empty($_POST['userPassword'])) {
		$userEmail = $_POST['userEmail'];
		$userPassword = $_POST['userPassword'];
		$ed = $qo->getSingleData("SELECT hostPassword FROM myblog.users WHERE hostEmail = '$userEmail'");
		if($userPassword == $ed) {
			session_start();
			$_SESSION['username'] = $qo->getSingleData("SELECT nickname FROM myblog.users WHERE hostEmail = '$userEmail'");
			$source = $_GET['source'];
			if($source === 'home') {
				header('location: MyBlogHome.php');
			} else {
				header("location: MyBlogPost.php?title=$source");
			}			
		} else {
			echo "<a style = 'position: absolute; top: 50%'>The email and password doesnt match</a>";
		}
	} else {
		echo "<a style = 'position: absolute; top: 50%'>Please fill all the fields</a>";
	}
	}
?>