<?php 

	require_once 'MyBlogView.php';
	
?>
	<main style = 'float:left; width :80%; position: absolute; top: 100px' >
	<form method="post" action = "" width = 80%>
			<table style = "border:2px solid black">
				<tbody>
					<tr><td><label>nickname *</label></td><td><input type="text" name = "userRegNickname" size = "10"></td></tr>
					<tr><td><label>email adress *</label></td><td><input type="text" name = "userRegEmail" size = "20"></td></tr>
					<tr><td><label>password *</label></td><td><input type="password" name = "userRegPassword1" size = "10"></td></tr>
					<tr><td><label>password again *</label></td><td><input type="password" name = "userRegPassword2" size = "10"></td></tr>
					<tr><td></td><td><input type="submit" name="regSubmit" value = "Registrate"></td></tr>
				</tbody>
			</table>
	</form>
	</main>
	
<?php
	if(isset($_POST['regSubmit'])) {
		$userRegNickname = $_POST['userRegNickname'];
		$userRegEmail = $_POST['userRegEmail'];
		$userRegPassword1 = $_POST['userRegPassword1'];
		$userRegPassword2 = $_POST['userRegPassword2'];		
		if(!empty($userRegNickname) & !empty($userRegEmail) & !empty($userRegPassword1) & !empty($userRegPassword2)) {
		if ($userRegPassword1 == $userRegPassword2) {
			$ed = $qo->getSingleData("SELECT hostPassword FROM myblog.users WHERE hostEmail = '$userRegEmail'");
			if($ed == "") {
				$qo->getSingleData("INSERT INTO myblog.users VALUES (null, '$userRegEmail', '$userRegPassword1', '$userRegNickname', now(), 'false');");
				session_start();
				$_SESSION["username"] = $userRegNickname;
				header('location: MyBlogLogIn.php');
			} else {
				echo "<a stlye = 'position: absolute; top: 500px;' >this email adress has registration</a>";
			}			
		} else {
			echo "<a stlye = 'position: absolute; top: 500px;' >Your repeated password doesnt match</a>";
			 
		}
	}else {
		echo "<a style = 'position: absolute; top: 50%'>Please fill all the fields</a>";
	}
	}
?>