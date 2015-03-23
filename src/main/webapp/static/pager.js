var Pager = {
	goToPage: function(url, page, csrfName, csrfTok) {
		var data = {
				page: (page - 1 >= 0 ? page - 1 : 0)
		};
		data[csrfName] = csrfTok;
		
		$("#userTable").load(url, data);
		return false;
	}	
};