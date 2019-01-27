package handlers.ioHandler;

public enum Command {
    //TODO get list from file
    stats(new String[]{"show_stats", "stats", "stat"}),
    inventory(new String[]{"inv", "open_inventory"}),
    move(new String[]{"move", "go"}),
    potion(new String[]{"drink", "use_potion"}),
    weapon(new String[]{"change_weapon"}),
    exit(new String[]{"leave", "exit_game", "exit"}),
    map(new String[]{"show_map", "map", "mep"}),
    no(new String[]{"no", "nope"}),
    yes(new String[]{"yes", "yeah",});

    public String[] text;

    Command(String[] text) {
        this.text = text;
    }
}
