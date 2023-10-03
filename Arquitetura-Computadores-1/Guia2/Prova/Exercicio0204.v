module Exercicio0204;
    real x = 0.312;
    integer q [0:2];
    reg[0:2][1:0] b;

    initial 
        begin: main
            q[0] = 3;
            q[1] = 1;
            q[2] = 2;
            b[0] = q[0];
            b[1] = q[1];
            b[2] = q[2]; 
            $display("%.3f", x);
            $display("%b", b);
        end
endmodule