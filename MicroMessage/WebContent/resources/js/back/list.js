/**
 * 调用后台的批量删除方法
 * @param basePath
 * @returns
 */
function deleteBatch(basePath) {
	$("#mainForm").attr("acrion", basePath + "DeleteBatchServlet.action");
	$("#mainForm").submit();
}