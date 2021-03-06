/**
 * PARAMETER.java
 */
package com.sdc.graphx_example.command_line;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;


public enum PARAMETER {
    SPARK_MASTER ("spark-master","Master url of the cluster (e.g. spark://23.195.26.187:7077).", true, String.class, false)
    ,RUN_TYPE( "run-type",String.format("Run type. Available values: %s."
            , Arrays.toString(com.sdc.graphx_example.command_line.RUN_TYPE.getValues().toArray()))
            , true, String.class, false)
    ,OSM_NODES_FILE ( "osm-nodes-file","Parquet osm nodes file path.", true, String.class, false)
    ,OSM_WAYS_FILE ( "osm-ways-file","Parquet osm ways file path.", true, String.class, false)
    ,NODES_FILE ( "nodes-file","Parquet internal network format nodes file path.", true, String.class, false)
    ,LINKS_FILE ( "links-file","Parquet internal network format links file path.", true, String.class, false)
    ,OSM_CONVERTER_NODES_REPARTITION_OUTPUT ("osmc-nodes-repartition-output","Repartition of the converted (from osm) nodes.", true, Integer.class, false)
    ,OSM_CONVERTER_LINKS_REPARTITION_OUTPUT ("osmc-links-repartition-output","Repartition of the converted (from link) links.", true, Integer.class, false)
    ,OSM_CONVERTER_PERSIST_NODES ("osmc-persist-nodes"
            , String.format("If true perssist the converted nodes. Default = %s",ParameterDefault.DEFAULT_OSM_CONVERTER_PERSIST_NODES)
            , true, Boolean.class, false)
    ,OSM_CONVERTER_PERSIST_LINKS ("osmc-persist-links"
            , String.format("If true perssist the converted links. Default = %s",ParameterDefault.DEFAULT_OSM_CONVERTER_PERSIST_LINKS)
            , true, Boolean.class, false)    
    ,OSM_CONVERTER_NETWORK_OUTPUT_FORMAT( "osmc-net-out-format",String.format("Network output format. Available values: %s."
            , Arrays.toString(com.sdc.graphx_example.command_line.NETWORK_OUTPUT_FORMAT.getValues().toArray()))
            , true, String.class, false)
    ,SP_COST_FUNCTION ("sp-cost-function","Repartition of the converted (from link) links.", true, String.class, false)    
    ,SP_SOURCE_LON ("sp-source-lon","Longitude in wgs84 of the shortest path source point", true, Integer.class, false)
    ,SP_SOURCE_LAT ("sp-source-lat","Latitude in wgs84 of the shortest path source point", true, Integer.class, false)    
    ,SP_NEAREST_DISTANCE ("sp-nearest-distance","The first distance in meters to consider searching for the nearest node to the source point", true, Integer.class, false)
    ,SP_NEAREST_ATTEMPS ("sp-nearest-attemps","The number of attemps to search for the nearest node to the source point."
            + " In each attemp the first search distance is enlarged by the extension factor parameter", true, Integer.class, false)
    ,SP_NEAREST_FACTOR ("sp-nearest-factor","Extension factor used to enlarge the distance in which to search the nearest node", true, Integer.class, false)    
    ,SP_RANDOM_GRAPH_NUM_VERTICES ("sp-random-graph-num-vertices","Number of vertices for the log normal graph generation", true, Integer.class, false)
    ,SP_GRAPH_REPARTITION ("sp-graph-repartition", String.format("Number of partitions of the graph vertices and edges."
            + " If < 1 skip the repartition. Default = %s", ParameterDefault.DEFAULT_SP_GRAPH_REPARTITION), true, Integer.class, false)    
    ,OUTOUT_DIR ( "output-dir","Output directory file path.", true, String.class, false)
    ;
    
    private String longOpt;
    private String description;
    private boolean hasArg;
    private Class<?> optionType;
    private boolean required;


    

    /**
     * @param longOption 
     * @param description
     * @param hasArg
     * @param optionType
     * @param required 
     */
    private PARAMETER(String longOption,
            String description, boolean hasArg, Class<?> optionType,
            boolean required) {
        this.longOpt = longOption;
        this.description = description;
        this.hasArg = hasArg;
        this.optionType = optionType;
        this.required = required;
    }
    
    public boolean isRequired() {

        return this.required;
    }
    @SuppressWarnings("static-access") 
    public Option createOption() {

        OptionBuilder builder = OptionBuilder
                .withLongOpt(getLongOpt()).hasArg(hasArg())
                .withDescription(getDescription())
                .withType(getOptionType());
        if (isRequired())
            builder.isRequired();

        return builder.create();

    }

    public boolean hasArg() {

        return hasArg;
    }

    public Class<?> getOptionType() {

        return optionType;
    }

    public String getDescription() {

        return description;
    }



    public static List<String> getLongOptions() {

        return Arrays.asList(PARAMETER.values()).stream()
                .map(PARAMETER::getLongOpt).collect(Collectors.toList());

    }

    
    /**
     * @return the {@link PARAMETER#longOpt}
     */
    public String getLongOpt() {
    
        return longOpt;
    }
    
    
}