package Models;

import Constants.ProjectConstants;
import Controller.MainGameEngine;
import Controller.MapController;
import Exceptions.CommandValidationException;
import Utils.CommandHandler;

import java.io.IOException;

/**
 * The type Phase.
 */
public abstract class Phase {

    /**
     * The D current state.
     */
    CurrentState d_currentState;

    /**
     * The D map controller.
     */
    MapController d_mapController = new MapController();

    /**
     * The D main game engine.
     */
    MainGameEngine d_mainGameEngine;

    /**
     * Instantiates a new Phase.
     *
     * @param p_currentState   the p current state
     * @param p_mainGameEngine the p main game engine
     */
    public Phase(CurrentState p_currentState, MainGameEngine p_mainGameEngine) {
        this.d_currentState = p_currentState;
        this.d_mainGameEngine = p_mainGameEngine;
    }

    /**
     * Init phase.
     */
    public abstract void initPhase();

    /**
     * Handle command.
     *
     * @param p_inputCommand the p input command
     * @throws CommandValidationException the command validation exception
     * @throws IOException                the io exception
     */
    public void handleCommand(String p_inputCommand) throws CommandValidationException, IOException {
        commandHandler(p_inputCommand, null);
    }

    /**
     * Handle command.
     *
     * @param p_inputCommand the p input command
     * @param p_player       the p player
     * @throws CommandValidationException the command validation exception
     * @throws IOException                the io exception
     */
    public void handleCommand(String p_inputCommand, Player p_player) throws CommandValidationException, IOException {
        commandHandler(p_inputCommand, p_player);
    }

    /**
     * Command handler.
     *
     * @param p_inputCommand the p input command
     * @param p_player       the p player
     * @throws CommandValidationException the command validation exception
     * @throws IOException                the io exception
     */
    public void commandHandler(String p_inputCommand, Player p_player) throws CommandValidationException, IOException {
        CommandHandler l_commandHandler = new CommandHandler(p_inputCommand);
        String l_mainCommand = l_commandHandler.getMainCommand();
        boolean l_mapAvailable = false;
        if(d_currentState.getD_map() != null){
            l_mapAvailable = true;
        }
        switch (l_mainCommand){
            case "loadmap":
                loadMap(l_commandHandler);
                d_currentState.getD_modelLogger().setD_message("Map Loaded Success", "type-1");
                break;
            case "editmap":
                editMap(l_commandHandler);
                d_currentState.getD_modelLogger().setD_message("Editmap command executed successfully", "type-1");
                break;
            case "editcountry":
                if (!l_mapAvailable) {
                    System.out.println(ProjectConstants.MAP_NOT_AVAILABLE_EDIT_COUNTRY);
                    d_currentState.getD_modelLogger().setD_message("Entered command: editCountry. Map is not available. ","type-1");
                } else {
                    editCountry(l_commandHandler);
                    d_currentState.getD_modelLogger().setD_message("Entered command: editcountry. Country edited successfully","");
                }
                break;
            case "editcontinent":
                if(!l_mapAvailable){
                    System.out.println(ProjectConstants.MAP_NOT_AVAILABLE);
                    d_currentState.getD_modelLogger().setD_message("Entered command: editcontinent. Map is not available. ","type-1");
                }
                else{
                    editContinent(l_commandHandler);
                    d_currentState.getD_modelLogger().setD_message("Entered command: editcontinent. Continent edited successfully. ","type-1");
                }
                break;
            case "editneighbour":
                if(!l_mapAvailable){
                    System.out.println(ProjectConstants.MAP_NOT_AVAILABLE);
                    d_currentState.getD_modelLogger().setD_message("Entered command: editneighbour. Map is not available. ","type-1");
                }
                else {
                    editNeighbourCountry(l_commandHandler);
                    d_currentState.getD_modelLogger().setD_message("Entered command: editneighbour. Neighbour edited successfully. ","type-1");
                }
                break;
            case "showmap":
                if(!l_mapAvailable){
                    System.out.println(ProjectConstants.MAP_NOT_AVAILABLE);
                    d_currentState.getD_modelLogger().setD_message("Entered command: showmap. Map is not available. ","type-1");
                }
                else {
                    showMap();
                    d_currentState.getD_modelLogger().setD_message("Entered command: showmap. showmap executed successfully. ","type-1");
                }
                break;
            case "gameplayer":
                if (!l_mapAvailable) {
                    System.out.println(ProjectConstants.MAP_NOT_AVAILABLE_PLAYERS);
                    d_currentState.getD_modelLogger().setD_message("Entered command: gameplayer. Map is not available. ","type-1");
                }
                else {
                    gamePlayer(l_commandHandler);
                    d_currentState.getD_modelLogger().setD_message("Entered command: gameplayer. gameplayer executed successfully. ","type-1");
                }
                break;
            case "assigncountries":
                if (!l_mapAvailable) {
                    System.out.println(ProjectConstants.MAP_NOT_AVAILABLE_ASSIGN_COUNTRIES);
                    d_currentState.getD_modelLogger().setD_message("Entered command: assigncountries. Map is not available. ","type-1");
                }
                else {
                    assignCountries(l_commandHandler);
                    d_currentState.getD_modelLogger().setD_message("Entered command: assigncountries. assigncountries executed successfully. ","type-1");
                }
                break;
            case "validatemap":
                if (!l_mapAvailable) {
                    System.out.println(ProjectConstants.MAP_NOT_AVAILABLE);
                    d_currentState.getD_modelLogger().setD_message("Entered command: validatemap. Map is not available. ","type-1");
                } else {
                    validateMap(l_commandHandler);
                    d_currentState.getD_modelLogger().setD_message("Entered command: validatemap. validatemap executed successfully. ","type-1");
                }
                break;
            case "savemap":
                if (!l_mapAvailable) {
                    System.out.println(ProjectConstants.MAP_NOT_AVAILABLE);
                    d_currentState.getD_modelLogger().setD_message("Entered command: savemap. Map is not available. ","type-1");
                } else {
                    saveMap(l_commandHandler);
                    d_currentState.getD_modelLogger().setD_message("Entered command: savemap. savemap executed successfully. ","type-1");
                }
                break;
            case "deploy":
                if (!l_mapAvailable) {
                    System.out.println(ProjectConstants.MAP_NOT_AVAILABLE);
                    d_currentState.getD_modelLogger().setD_message("Entered command: deploy. Map is not available. ","type-1");
                } else {
                    deploy(p_inputCommand, p_player);
                    d_currentState.getD_modelLogger().setD_message("Entered command: deploy. deploy executed successfully. ","type-1");
                }
                break;
            case "exit":
                d_currentState.getD_modelLogger().setD_message("Entered command: exit. Exited successfully.","Type3");
                d_currentState.getD_modelLogger().setD_message("---------------Game Session Closed---------------","Type3");
                System.out.println("Closing Game....");
                System.exit(0);
                break;
        }
    }

    /**
     * Deploy.
     *
     * @param p_inputCommand the p input command
     * @param p_player       the p player
     */
    protected abstract void deploy(String p_inputCommand, Player p_player);


    /**
     * Load map.
     *
     * @param lCommandHandler the l command handler
     * @throws CommandValidationException the command validation exception
     */
    protected abstract void loadMap(CommandHandler lCommandHandler) throws CommandValidationException;

    /**
     * Edit map.
     *
     * @param lCommandHandler the l command handler
     * @throws CommandValidationException the command validation exception
     * @throws IOException                the io exception
     */
    protected abstract void editMap(CommandHandler lCommandHandler) throws CommandValidationException, IOException;

    /**
     * Edit country.
     *
     * @param lCommandHandler the l command handler
     * @throws CommandValidationException the command validation exception
     */
    protected abstract void editCountry(CommandHandler lCommandHandler) throws CommandValidationException;

    /**
     * Edit continent.
     *
     * @param lCommandHandler the l command handler
     * @throws CommandValidationException the command validation exception
     */
    protected abstract void editContinent(CommandHandler lCommandHandler) throws CommandValidationException;

    /**
     * Edit neighbour country.
     *
     * @param lCommandHandler the l command handler
     * @throws CommandValidationException the command validation exception
     */
    protected abstract void editNeighbourCountry(CommandHandler lCommandHandler) throws CommandValidationException;

    /**
     * Show map.
     *
     * @throws CommandValidationException the command validation exception
     */
    protected abstract void showMap() throws CommandValidationException;

    /**
     * Game player.
     *
     * @param lCommandHandler the l command handler
     * @throws CommandValidationException the command validation exception
     */
    protected abstract void gamePlayer(CommandHandler lCommandHandler) throws CommandValidationException;

    /**
     * Assign countries.
     *
     * @param lCommandHandler the l command handler
     * @throws CommandValidationException the command validation exception
     * @throws IOException                the io exception
     */
    protected abstract void assignCountries(CommandHandler lCommandHandler) throws CommandValidationException, IOException;

    /**
     * Validate map.
     *
     * @param lCommandHandler the l command handler
     * @throws CommandValidationException the command validation exception
     */
    protected abstract void validateMap(CommandHandler lCommandHandler) throws CommandValidationException;

    /**
     * Save map.
     *
     * @param lCommandHandler the l command handler
     * @throws CommandValidationException the command validation exception
     */
    protected abstract void saveMap(CommandHandler lCommandHandler) throws CommandValidationException;

    /**
     * Print invalid command in phase.
     */
    public void printInvalidCommandInPhase(){
        d_currentState.getD_modelLogger().setD_message("Invalid Command entered for this phase.","Type-1");
    }

}