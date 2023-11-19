
module dff(q,qnot,d,clk);
	output q,qnot;
	input d,clk;
	reg q,qnot;

	always @(posedge clk) begin
		q <= d; 
		qnot < ~d;
	end

endmodule dff