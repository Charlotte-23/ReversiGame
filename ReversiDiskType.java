/**
 * ReversiDiskType enum Class
 */
public enum ReversiDiskType {
    X( "X" ),
    O( "O" ),
    EMPTY( " " );

    private String str;

    ReversiDiskType( String str ) {
        this.str = str;
    }

    @Override
    public String toString() {
        return str;
    }
};