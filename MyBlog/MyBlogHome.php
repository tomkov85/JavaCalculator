<?php

	require_once 'MyBlogView.php';
	if($_GET["tag"] == null) {
		$qoResult = $qo->setContentTable("SELECT * FROM myblog.posts ORDER BY postDate DESC");
	} else {
	   $tag = $_GET["tag"];
		$qoResult = $qo->setContentTable("SELECT * FROM myblog.posts WHERE tags = '$tag' ORDER BY postDate DESC");
	}
	$commentCounter = $qo->setContentTableNum( "SELECT count(postId) FROM myblog.comments GROUP BY postId");
	?>
	<main style = 'float:left; width :80%; position: absolute; top: 100px' >
	<div>
	<?php
	$userRights = false;
	
	if(!empty($_SESSION["username"])) {
		$username = $_SESSION["username"];
		$userRights = $qo->getSingleData("SELECT host FROM myblog.users WHERE nickname = '$username'");
	}
	
	if($userRights) {
		?><button onclick = "location.href = 'MyBlogManagePost.php?title=0'">New Post</button><br>
		
		<?php foreach($qoResult as $row) {
		$cc = $qo->getTable($row->id, $commentCounter, 0);		
		?>
		<table style = 'width: 100%;'><tbody> 
		<tr><td style = 'width: 70%;'><a href = 'MyBlogPost.php?title=<?php echo $row->slug?>'><?php echo $row->title?></a></td><td>Author:<?php echo $row->author,"  Date: ", $row->postDate?></td></tr>
		<tr><td><?php echo $row->postText?></td></tr>
		<tr><td><a>Comments:<?php echo $cc ?></a></td><td><a href = 'MyBlogPost.php?title=<?php echo $row->slug?>'>More</a></td></tr>
		</tbody></table>
		<button onclick = "location.href = 'MyBlogManagePost.php?title=<?php echo $row->slug?>'">Modify</button><button onclick = "location.href = 'MyBlogManagePost.php?delete=<?php echo $row->slug?>'">Delete</button>
		<br><br>
		<?php
		}
	} else {
	foreach($qoResult as $row) {
		$cc = $qo->getTable($row->id, $commentCounter, 0);
		?>
		<table style = 'width: 100%;'><tbody>
		<tr><td style = 'width: 70%;'><a href = 'MyBlogPost.php?title=<?php echo $row->slug?>'><?php echo $row->title?></a></td><td>Author:<?php echo $row->author,"  Date: ", $row->postDate?></td></tr>
		<tr><td> <?php echo $row->postText ?></td></tr>
		<tr> </tr>
		<tr><td><b>Tags:</b><a href ='MyBlogHome.php?tag= <?php echo $row->tags ?>'> <?php echo $row->tags?></a></td></tr>
		<tr><td><a>Comments:<?php echo $cc ?> </a></td><td><a href = 'MyBlogPost.php?title= <?php echo $row->slug ?>'>More</a></td></tr>
		</tbody>
		</table>
		<br>
		<?php
	} 
	} ?>
	</div>
	</main>
