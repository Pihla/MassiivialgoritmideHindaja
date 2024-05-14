package main.kasutajaliides;

public class ViganeSisendException extends Exception {
    public ViganeSisendException(String message) {
        //TODO vb peaks selle tegelikult ära kustutama kuna nkn peab muid exceptioneid ka catchima. nt tööala 0 0 vistkab. aga vb peaks igal pool praegust exceptionit kasitama
        super(message);
    }
}
