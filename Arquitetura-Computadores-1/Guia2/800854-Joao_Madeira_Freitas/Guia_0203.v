/*
Guia_0203.v
João Madeira Carneiro Braga de Freitas
800854
*/

module Guia_0203;

task realBin2Base;
	input reg[31:0] bin;
	input integer base;
	input integer pontoFixo;
	integer i, j, k, inteira, temp1, resto;
	real decimal , numero, temp2;
	integer arrayint [0:31];
	integer arrayfrac [0:31];
	begin	
		decimal = 0.0;
		inteira = 0;
		j = 0;
		k = 0;
		resto = 0;
		temp1 = 0;
		temp2 = 0.0;
		for(i = pontoFixo; i >= 0; i--)begin
			decimal = decimal + bin[i]*(1.0/(2**(pontoFixo - i + 1)));
		end
		for(i = pontoFixo + 1;i < 31; i++)begin
			inteira = inteira + bin[i]*(2**(i - pontoFixo - 1));
		end
		numero = decimal + inteira;
	
		while(inteira > 0)begin
			resto = inteira%base;
			inteira = inteira/base;
			arrayint[j] = resto;
			j++;
			
		end
		
		while(decimal != 0.0)begin
			temp2 = decimal*base;
			temp1 = temp2;
			if(temp1 > temp2)begin
				temp1--;
			end
			decimal = temp2 - temp1;
			$write("%f",decimal);
			arrayfrac[k] = temp1;
			k++;
		end
		$write("A conversão de %b para a base (%d) gera: ",bin,base);
		for(i = j - 1; i >= 0; i--)begin
			$write("%d",arrayint[i]);
		end
		$write(".");
		for(i = 0; i < k; i++)begin
			$write("%d",arrayfrac[i]);
		end
		
	end
endtask
//define data

reg[9:0] a = 10'b0010010;
reg[9:0] b = 10'b0100101;
reg[9:0] c = 10'b0101011;
reg[9:0] d = 10'b1110001;
reg[9:0] e = 10'b11101001;


initial
	begin:main
		$display("Guia_0203");
		realBin2Base(e,4,3);
	end
endmodule
