package com.fra.ristorante;

public @interface TipoPortata {
    enum Portata {ANTIPASTI,PRIMI,SECONDI,DOLCI}

    Portata value();
};