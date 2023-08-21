/*
Guia_0202.v
João Madeira Carneiro Braga de Freitas
800854
*/

module Guia_0202;

real a = 0.75;
real b = 1.375; 
real c = 2.625; 
real d = 6.875; 
real e = 11.03125;
reg[9:0] aux = 10'b0;


task printBinaryFractionalPart;
	input integer start;
	input reg[9:0] bin;
	integer i;
	begin
		$write(".");
		for(i = start; i >= 0; i--)begin
			$write("%b",bin[i]);
		end
		$write("(2)\n");
	end	

endtask
task double2bin;
	input real number;
	reg[9:0] bin;
	reg[9:0] temp;
	integer array [0:9];
	integer parteInteira;
	integer i, j, k, p;
	real parteFracionaria;
	real auxiliar;
	begin
		parteInteira = number;
		if(parteInteira > number)begin
			parteInteira--;
			end
		temp = parteInteira;
		parteFracionaria = number - parteInteira;
    		i = 0;
    		p = 0;
		while(parteFracionaria != 0.0)begin
			auxiliar = parteFracionaria*2;
			parteInteira = auxiliar;
			if(parteInteira > auxiliar)begin
				parteInteira--;
				end
			parteFracionaria = auxiliar - parteInteira;
			array[i] = parteInteira;
			i++;
			end
			k = i - 1;
		while(i-1 >= 0)begin
			bin[p] = array[i-1];
			$display("bin = ",bin[p]);
			i--;
			p++;
			end
		$write("Conversão de %f(10) para",number);
		$write(" %b",temp);
		printBinaryFractionalPart(k,bin);	
	end
endtask
initial
	begin: main
	$display("Guia_0202");
	double2bin(a);
	double2bin(b);
	double2bin(c);
	double2bin(d);
	double2bin(e);
	end
endmodule
