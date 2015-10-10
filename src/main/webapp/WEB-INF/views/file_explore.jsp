<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" charset="utf-8">
<head>
	<title>파일관리자</title>
	<link rel="stylesheet" type="text/css" href="resources/css/bootstrap/bootstrap.css" />
	<link rel="stylesheet" type="text/css" href="resources/css/bootstrap/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="resources/css/bootstrap/custom.css" />
	<script type="text/javascript" src="resources/js/jquery/jquery-2.1.4.js"></script>
	<script type="text/javascript" src="resources/js/jquery/jquery-ui.js"></script>
	<script>
		$(function() {

			$('.fileAction').children("span").each(function(){
				$(this).hover(function() {$(this).css('cursor','pointer');},function() {$(this).css('cursor','auto');});
				
			});
			
		});
		
		function confirmRename(source_name){
			
			var rename_info = new Object();
			rename_info.source_name = source_name;
			
			$("#rename-dialog").dialog({
				resizable : false,
				modal : true,
				position: { my: "center", at: "top", of: window },
				open: function(event, ui) {
					$(this).closest('.ui-dialog').find('.ui-dialog-titlebar-close').hide();
					$(this).html('<input type="text" id="target_name" />');
					},
				buttons : {
					
					확인 : function() {
						
						if($("#target_name").val() == ""){
							alert("새파일명을 입력해 주세요.");
							return false;
						}
						rename_info.target_name = $("#target_name").val();
						
						$.ajax({
							type: "POST",
							url: "/renameFile.do",
							dataType: "json",
							data: JSON.stringify(rename_info),
							contentType: "application/json",
							success: function(result){
								
								if(result){
									alert("파일명이 변경되었습니다.")
									location.reload();
								}else{
									alert("파일명 변경 실패")
								}
							},
							error:function(e){
								alert(e.responseText);  
							} 
						});	
					},
					취소 : function() {
						$(this).dialog("close");
					}
				}
			});
			
		}
		
		function confirmDelete(file_name){
			
			$("#delete-dialog").dialog({
				resizable : false,
				modal : true,
				position: { my: "center", at: "top", of: window },
				open: function(event, ui) {
					$(this).closest('.ui-dialog').find('.ui-dialog-titlebar-close').hide();
					$(this).html(file_name + '파일이 삭제됩니다.');
					},
				buttons : {
					
					확인 : function() {
						
						$.ajax({
							type: "POST",
							url: "/deleteFile.do",
							dataType: "json",
							data: {"delete_file_name": file_name},
							success: function(result){
								
								alert(result);
								
								if(result){
									alert("삭제되었습니다.")
									location.reload();
								}else{
									alert("파일삭제 실패");
								}
							},
							error:function(e){
								alert(e.responseText);  
							} 
						});	
					},
					취소 : function() {
						$(this).dialog("close");
					}
				}
			});
		}
		
		function confirmDownlaod(file_path){
			
			location.href="/downloadFile.do?file_path="+file_path;
			
		}
		
	</script>
</head>
<body>
	<div id="rename-dialog" title="파일이름 변경" ></div>
	<div id="delete-dialog" title="파일삭제" ></div>
	
	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel-heading">
			<a href="movePath.do?path=C:/" title="C드라이브" >C드라이브</a>
			<a href="movePath.do?path=D:/" title="D드라이브" >D드라이브</a>
		</div>
		<div class="panel-body">
			<h4>현재 디렉토리 : ${dir_info.current_path}
			</h4>
		</div>

		<!-- Table -->
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th class="text-center" width="50px">#</th>
					<th class="text-center">파일명</th>
					<th class="text-center" width="15%">최근 수정일자</th>
					<th class="text-center" width="10%">파일크기</th>
					<th class="text-center" width="20%">기능</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${dir_info.root_directory}" >
					<tr>
						<th scope="row">
						</th>
						<td>
							<a href="movePath.do?path=${dir_info.parent_path}" title="${file.file_name}" >[상위 디렉토리]</a>
						</td>
						<td></td>
						<td class="text-right"></td>
						<td></td>
					</tr>
				</c:if>
				<c:forEach items="${dir_info.files}" var="file">
					<tr>
						<th scope="row" class="text-center">
							<input type="checkbox" >
						</th>
						<td>
							<c:choose>
								<c:when test="${file.is_directory}">
									<span class="glyphicon glyphicon-folder-open" style="margin-right:10px"></span>
									<a href="movePath.do?path=${dir_info.current_path}/${file.file_name}" title="${file.file_name}" >${file.file_name}</a>
								</c:when>
								<c:when test="${file.is_file}">
									<span class="glyphicon glyphicon-file" style="margin-right:10px"></span>
									${file.file_name}
								</c:when>
							</c:choose>
						</td>
						<td class="text-center" >${file.last_modified_dt}</td>
						<td class="text-right">${file.file_size}</td>
						<td class="fileAction">
							<c:if test="${!file.is_directory}">
								<span onclick="confirmDownlaod('${dir_info.current_path}/${file.file_name}'">다운로드</span> |
								<span onclick="confirmDelete('${dir_info.current_path}/${file.file_name}');">삭제</span> |
							</c:if>
							<span onclick="confirmRename('${dir_info.current_path}/${file.file_name}');">이름변경</span>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>

