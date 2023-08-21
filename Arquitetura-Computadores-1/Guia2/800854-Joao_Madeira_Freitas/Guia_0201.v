/*
Guia_0201.v
João Madeira Carneiro Braga de Freitas
800854
*/


module guia_0201;
reg [6:0] a = 7'b001101; 
reg [6:0] b = 7'b010011; 
reg [6:0] c = 7'b010101;
reg [6:0] d = 7'b111001; 
reg [6:0] e = 7'b1111011;
real aux = 0.0;


function real bin2double;
	input [6:0] bin;
	input integer pontoFixo;
	integer parteInteira;
	integer i;
	integer j;
	real parteFracionaria;
	begin
	parteInteira = 0;
	parteFracionaria = 0;
	for(i = 0; i < pontoFixo; i = i + 1)begin
		if(bin[i])begin
			parteFracionaria = parteFracionaria + (1/(2.0**(pontoFixo-i)));
			end
		end
	for(j = pontoFixo; j < 7; j = j + 1)begin
		if(bin[j])begin
			parteInteira = parteInteira + (2**(j-pontoFixo));
			end
		end
	bin2double =  parteInteira + parteFracionaria;
	end
endfunction

initial
begin:main
	$display("Guia_0201");
	$display("a(2) = %b",a);
	aux = bin2double(a,5);
	$display("a(10) = %f\n",aux);
	
	$display("b(2) = %b",b);
	aux = bin2double(b,5);
	$display("b(10) = %f\n",aux);
	
	$display("c(2) = %b",c);
	aux = bin2double(c,5);
	$display("c(10) = %f\n",aux);
	
	$display("d(2) = %b",d);
	aux = bin2double(d,5);
	$display("d(10) = %f\n",aux);
	
	$display("e(2) = %b",e);
	aux = bin2double(e,5);
	$display("e(10) = %f\n",aux);
	
	end
endmodule
