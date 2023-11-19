/*
   João Madeira Carneiro Braga de Freitas
   800854
*/

`include "ff.v"

module movDireita(clk,data,a,b,c,d,e,f);
	input clk,data;
	output a,b,c,d,e,f;
	reg a,b,c,d,e,f,g;
	wire oanot,obnot,ocnot,odnot,oenot,ofnot;
	always @(posedge clk) begin
		dff FF1(a,oanot,data,clk);
		dff FF2(b,obnot,a,clk);
		dff FF3(c,ocnot,b,clk);
		dff FF4(d,odnot,c,clk);
		dff FF5(e,oenot,d,clk);
		dff FF6(f,ofnot,e,clk);
	end
endmodule //movDireita

module movEsquerda(clk,data,a,b,c,d,e,f);
   input clk,data;
   output a,b,c,d,e,f;
   reg a,b,c,d,e,f,g;
   wire oanot,obnot,ocnot,odnot,oenot,ofnot;
   always @(posedge clk) begin
      dff FF1(f,ofnot,data,clk);
      dff FF2(e,oenot,f,clk);
      dff FF3(d,odnot,e,clk);
      dff FF4(c,ocnot,d,clk);
      dff FF5(b,obnot,c,clk);
      dff FF6(a,oanot,b,clk);
   end
endmodule //movEsquerda

module ringDireita(clk,data,a,b,c,d,e,f);
   input clk,data;
   output a,b,c,d,e,f;
   reg a,b,c,d,e,f,g;
   wire oanot,obnot,ocnot,odnot,oenot,ofnot,orRing;
   always @(posedge clk) begin
	  or OR(orRing,f,data);
      dff FF1(a,oanot,orRing,clk);
      dff FF2(b,obnot,a,clk);
      dff FF3(c,ocnot,b,clk);
      dff FF4(d,odnot,c,clk);
      dff FF5(e,oenot,d,clk);
	  dff FF6(f,ofnot,e,clk);
   end
endmodule //ringDireita

module twistedEsquerda(clk,data,a,b,c,d,e,f);
   input clk,data;
   output a,b,c,d,e,f;
   reg a,b,c,d,e,f,g;
   wire oanot,obnot,ocnot,odnot,oenot,ofnot,orRing;
   always @(posedge clk) begin
      or OR(orRing,ofnot,data);
      dff FF1(a,oanot,orRing,clk);
      dff FF2(b,obnot,a,clk);
      dff FF3(c,ocnot,b,clk);
      dff FF4(d,odnot,c,clk);
      dff FF5(e,oenot,d,clk);
	  dff FF6(f,ofnot,e,clk);
   end
endmodule //ringDireita


module serialParalelo(clk,data,a,b,c,d,e,f);
   input clk,data;
   output a,b,c,d,e,f;
   reg a,b,c,d,e,f,g;
   wire oanot,obnot,ocnot,odnot,oenot,ofnot;
   always @(posedge clk) begin
      dff FF1(a,oanot,data,clk);
      dff FF2(b,obnot,a,clk);
      dff FF3(c,ocnot,b,clk);
      dff FF4(d,odnot,c,clk);
      dff FF5(e,oenot,d,clk);
	  dff FF6(f,ofnot,e,clk);
   end
endmodule //serialParalelo