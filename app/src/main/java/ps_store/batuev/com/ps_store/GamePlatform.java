package ps_store.batuev.com.ps_store;
public enum GamePlatform {
    PS3,
    PS4,
    Xbox,
    ;

    public static GamePlatform of(String value) {
        switch (value) {
            case "PlayStation 3":
                return PS3;
            case "PlayStation 4":
                return PS4;
            case "Xbox":
                return Xbox;
        }

        return null;
    }
}
