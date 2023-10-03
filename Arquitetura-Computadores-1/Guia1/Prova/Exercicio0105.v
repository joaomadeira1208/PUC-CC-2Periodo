module Exercicio0105;
    reg[0:8][7:0] s = "PUC-MINAS";
    reg[0:4][7:0] x;

    initial 
        begin: main
        $display("Exercicio 5 - Guia 1");
        $display("s = %s", s);
        $display("(16_ASCII) = %h", s);
        $display("(2_ASCII) = %b", s);
        
        x[0] = 8'o116;
        x[1] = 8'o157;
        x[2] = 8'o151;
        x[3] = 8'o164;
        x[4] = 8'o145;
        $display("116 157 151 164 145 (8) = %s (ASCII)", x);

        x[0] = 8'h4d;
        x[1] = 8'h2e;
        x[2] = 8'h20;
        x[3] = 8'h47;
        x[4] = 8'h2e;

        $display("4D 2E 20 47 2E (16) = %s (ASCII)", x);
       

        end

endmodule