package Constants;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * The type Project constants.
 */
public final class ProjectConstants implements Serializable {
    /**
     * The constant WIDTH.
     */
    public static final int WIDTH = 80;
    /**
     * The constant PLAYER_BEHAVIOR.
     */
    public static final List<String>  PLAYER_BEHAVIOR = Arrays.asList("Human","Aggressive", "Random", "Benevolent", "Cheater");
    /**
     * The constant INVALID_SAVEGAME_COMMAND.
     */
    public static final String INVALID_SAVEGAME_COMMAND = "Invalid command for savegame. Please provide savegame filename";
    /**
     * The constant INVALID_LOADGAME_COMMAND.
     */
    public static final String INVALID_LOADGAME_COMMAND = "Invalid command for loadgame. Please provide loadgame filename";

    /**
     * Instantiates a new Project constants.
     */
    public ProjectConstants() {
    }

    /**
     * The constant MAP_NOT_AVAILABLE_EDIT_COUNTRY.
     */
    public static final String MAP_NOT_AVAILABLE_EDIT_COUNTRY = "Cannot edit Country as map is not available. Please run editmap command";
    /**
     * The constant MAP_NOT_AVAILABLE.
     */
    public static final String MAP_NOT_AVAILABLE = "Map not available. Please use editmap command first.";
    /**
     * The constant MAP_NOT_AVAILABLE_PLAYERS.
     */
    public static final String MAP_NOT_AVAILABLE_PLAYERS = "Map is not available, can not add players. Please first load the map using 'loadmap' command.";
    /**
     * The constant MAP_NOT_AVAILABLE_ASSIGN_COUNTRIES.
     */
    public static final String MAP_NOT_AVAILABLE_ASSIGN_COUNTRIES = "Map is not available, can not assign country. Please first load the map using 'loadmap' command.";
    /**
     * The constant DEPLOY_ARMIES_MESSAGE.
     */
    public static final String DEPLOY_ARMIES_MESSAGE = "-------> Deploy armies to countries for each player: (Usage: 'deploy <country_name> <number_of_armies_to_deploy>')";
    /**
     * The constant INVALID_GAMEPLAYER_COMMAND.
     */
    public static final String INVALID_GAMEPLAYER_COMMAND = "Wrong command entered, Please enter the correct 'gameplayer' command.";
    /**
     * The constant INVALID_SAVEMAP_COMMAND.
     */
    public static final String INVALID_SAVEMAP_COMMAND = "Save map command is not correct. Use 'savemap filename' command.";
    /**
     * The constant SAVEMAP_FAILURE_MESSAGE.
     */
    public static final String SAVEMAP_FAILURE_MESSAGE = "An error occured while saving the map.";
    /**
     * The constant MAP_NOT_FOUND.
     */
    public static final String MAP_NOT_FOUND = "Map not Found!";
    /**
     * The constant VALID_MAP.
     */
    public static final String VALID_MAP = "Map is Valid";
    /**
     * The constant INVALID_MAP.
     */
    public static final String INVALID_MAP = "Map is not Valid";
    /**
     * The constant INVALID_VALIDATE_COMMAND.
     */
    public static final String INVALID_VALIDATE_COMMAND = "Validate map command is not correct. Use 'validatemap' command.";
    /**
     * The constant FILE_NOT_FOUND.
     */
    public static final String FILE_NOT_FOUND = "File not Found!";
    /**
     * The constant CORRUPTED_FILE.
     */
    public static final String CORRUPTED_FILE = "File not Found or corrupted file!";
    /**
     * The constant FILE_CREATED_SUCCESS.
     */
    public static final String FILE_CREATED_SUCCESS = "File has been created";
    /**
     * The constant FILE_ALREADY_EXISTS.
     */
    public static final String FILE_ALREADY_EXISTS = "File already exists";
    /**
     * The constant CANNOT_ADD_REMOVE_COUNTRY.
     */
    public static final String CANNOT_ADD_REMOVE_COUNTRY = "Can not Add/remove country.";
    /**
     * The constant CANNOT_ADD_REMOVE_NEIGHBOUR.
     */
    public static final String CANNOT_ADD_REMOVE_NEIGHBOUR = "Can not Add/remove Neighbour.";
    /**
     * The constant CANNOT_ADD_REMOVE_CONTINENT.
     */
    public static final String CANNOT_ADD_REMOVE_CONTINENT = "Can not Add/remove continent.";
    /**
     * The constant NO_CONTINENT_IN_MAP.
     */
    public static final String NO_CONTINENT_IN_MAP = "No Continents in this map. Can't save an incorrect map.";
    /**
     * The constant NO_COUNTRY_IN_MAP.
     */
    public static final String NO_COUNTRY_IN_MAP = "No Countries in this map. Can't save an incorrect map.";
    /**
     * The constant NO_BORDER_IN_MAP.
     */
    public static final String NO_BORDER_IN_MAP = "Border are not present. This is not a connected graph.";
    /**
     * The constant NO_PLAYER_IN_GAME.
     */
    public static final String NO_PLAYER_IN_GAME = "No players exist in the game. Please add players using 'gameplayer -add' command first.";
    /**
     * The constant INVALID_NO_OF_ARMIES.
     */
    public static final String INVALID_NO_OF_ARMIES = "Given deploy order can't be executed as armies in deploy order is more than players unallocated armies";
    /**
     * The constant ORDER_ADDED.
     */
    public static final String ORDER_ADDED = "Order has been added to queue for execution";
    /**
     * The constant COUNTRY_NOT_IN_CONTINENT.
     */
    public static final String COUNTRY_NOT_IN_CONTINENT = "This countries does not exist in this continent.";
    /**
     * The constant EMPTY_CONTINENT.
     */
    public static final String EMPTY_CONTINENT = "No countries exists in this continent.";
    /**
     * The constant NEIGHBOUR_ALREADY_EXISTS.
     */
    public static final String NEIGHBOUR_ALREADY_EXISTS = "Neighbour already exists.";
    /**
     * The constant NO_NEIGHBOUR_PRESENT.
     */
    public static final String NO_NEIGHBOUR_PRESENT = "No neighbouring countries present.";
    /**
     * The constant NAME_ALREADY_EXISTS.
     */
    public static final String NAME_ALREADY_EXISTS = "Name already exist, try some other name!";
    /**
     * The constant COUNTRY_DOES_NOT_EXIST.
     */
    public static final String COUNTRY_DOES_NOT_EXIST = "The entered country is not present.";
    /**
     * The constant CONTINENT_DOES_NOT_EXIST.
     */
    public static final String CONTINENT_DOES_NOT_EXIST = "The entered continent is not present.";
    /**
     * The constant ALL_CARDS.
     */
    public static final List<String> ALL_CARDS = Arrays.asList("bomb", "blockade", "airlift", "negotiate");
    /**
     * The constant NO_OF_CARDS.
     */
    public static final int NO_OF_CARDS = ALL_CARDS.size();
    /**
     * The constant INVALID_TOURNAMENT_MODE_COMMAND.
     */
    public static final String INVALID_TOURNAMENT_MODE_COMMAND = "Invalid command for Tournament mode, Provide command in format of : tournament -M <list_of_maps> -P <list_of_player_strategies> -G <number_of_games> -D <max_turns>";
    /**
     * The constant INVALID_MAP_FILE_COUNT.
     */
    public static final String INVALID_MAP_FILE_COUNT = "Invalid number of map files. Please provide 1 to 5 map files.";
    /**
     * The constant DUPLICATE_STRATEGY.
     */
    public static final String DUPLICATE_STRATEGY = "Duplicate strategy found. Please provide unique strategies for each player.";
    /**
     * The constant INVALID_STRATEGY.
     */
    public static final String INVALID_STRATEGY = "Invalid strategy found. Please provide valid strategy for each player from : 'Aggressive', 'Random', 'Benevolent', 'Cheater'";
    /**
     * The constant TOURNAMENT_PLAYER_BEHAVIOUR.
     */
    public static final List<String> TOURNAMENT_PLAYER_BEHAVIOUR = Arrays.asList("Aggressive", "Random", "Benevolent", "Cheater");
    /**
     * The constant INVALID_STRATEGY_COUNT.
     */
    public static final String INVALID_STRATEGY_COUNT = "Invalid number of strategies found. Please provide 2 to 4 strategies for each player.";
    /**
     * The constant INVALID_PLAYER_NUMBER.
     */
    public static final String INVALID_PLAYER_NUMBER = "Invalid number of players found. Please provide atleast 2 players for the tournament.";
    /**
     * The constant INVALID_GAME_COUNT.
     */
    public static final String INVALID_GAME_COUNT = "Invalid number of games found. Please provide 1 to 5 games for the tournament.";
    /**
     * The constant INVALID_TURN_COUNT.
     */
    public static final String INVALID_TURN_COUNT = "Invalid number of turns found. Please provide 10 to 50 turns for the tournament.";
}

