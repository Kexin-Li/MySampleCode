/**
 * 调用后台的批量删除方法
 * @param basePath
 * @returns
 */
function deleteBatch(basePath) {
	$("#mainForm").attr("acrion", basePath + "DeleteBatchServlet.action");
	$("#mainForm").submit();
}

/**
 * 修改当前页码，调用后台重新查询
 * @param currentPage
 * @returns
 */
function changeCurrentPage(currentPage) {
	$("#currentPage").val(currentPage);
	$("#mainForm").submit();
}