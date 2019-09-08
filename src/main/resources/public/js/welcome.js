jQuery(document).ready(function() {   
       
    jQuery.ajax({
	type : "POST",
	contentType : "application/json",
	url : "/getAllRaffleTickets",
	dataType : 'json',
	cache : false,
	timeout : 600000,
	success : function(data) {
	   
	    table = jQuery('#raffleTicketTable').DataTable({
		"data" : data,
		"columns" : [
		    {
			type: 'num',
			data : 'raffleTicketId'		    
		    }, 
		    {
			data : 'raffleKey'		    
		    }, 
		    {
			data : 'sid'
		    },
		    {
			data: 'winner',
			 render : function(t, type, row) {
				return (row.winner) ? '<span style="color:crimson; font-weight:bold;">WIN!</span>' : '';
			    }
		    }],
		"order" : [ [ 0, "desc" ] ],
		paging:false
	    });
	    
	    jQuery('#raffleTicketTable tbody').on('click', 'tr', function() {

		var tr = $(this);
		currentRow = table.row(this).data();

		console.log(currentRow);

		if ($(this).hasClass('selected')) {
		    $(this).removeClass('selected');		    
		} else {
		    table.$('tr.selected').removeClass('selected');
		    $(this).addClass('selected');	
		}
	    });
	},
	error : function(e) {
	    console.log("ERROR : ", e);
	}
    });

});

function generateRaffleWinner(){    
    
    jQuery.ajax({
	type : "POST",
	contentType : "application/json",
	url : "/generateRaffleWinner",
	dataType : 'json',
	cache : false,
	timeout : 600000,
	success : function(data) {
	    
	    alert("Lucky Winner Today is: "+data.sid + ", Raffle Ticket ID is: "+data.raffleKey);
	    location.reload();
	},
	error : function(e) {
	    console.log("ERROR : ", e);
	}
    });
}