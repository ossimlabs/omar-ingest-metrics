package omar.ingest.metrics

import io.swagger.annotations.*

import grails.converters.JSON
import omar.core.BindUtil

@Api( value = "ingestMetrics",
        description = "Ingest Metrics support"
)
class IngestMetricsController {
    def ingestMetricsService

    def index() {
        render ""
    }

    @ApiOperation( value = "Marks the start of the ingest process",
            produces = 'text/plain',
            httpMethod = 'POST',
            notes = """
The service api **startIngest** marks the start of the overall ingest process. This will create a new record initialized to the current time and use the ingestId to index this record. This will mark the record with a RUNNING status.  

## Parameter List

*   **ingestId**

    Pass the ingestId used to identify the ingest metric record

*   **description**

    General description for this record

**Additional Notes**

You can also pass the arguments as a JSON string and 
post to the URL.  The format supported:

```        
{
    "ingestId": "",
    "description": "",
}
```

## RETURNS
    The current values for the record in JSON format
    """)
    @ApiImplicitParams( [
            @ApiImplicitParam( name = 'ingestId', value = 'ID Used to identify the ingest record', dataType = 'string', paramType="query", required = true ),
            @ApiImplicitParam( name = 'description', value = 'ID Used to identify the ingest record', dataType = 'string', paramType="query", required = false ),
    ] )
    def startIngest(){
        def jsonData = request.JSON?request.JSON as HashMap:null
        def requestParams = params - params.subMap( ['controller', 'action'] )
        def cmd = new IngestCommand()
        if(jsonData) requestParams << jsonData

        // get map from JSON and merge into parameters
        if(jsonData) requestParams << jsonData
        BindUtil.fixParamNames( IngestCommand, requestParams )
        bindData( cmd, requestParams )

        HashMap result = ingestMetricsService.startIngest(cmd)
        String resultString =  (result as JSON).toString()
        response.status      = result.statusCode
        response.contentLength = resultString.length()

        render contentType: "application/json", text: result as JSON
    }

    @ApiOperation( value = "Marks the end of the ingest process",
            produces = 'text/plain',
            httpMethod = 'POST',
            notes = """
The service api **endIngest** marks the end of the overall ingest process. This will mark the record as finished. This will use the current date and time to mark the time of the call  

## Parameter List

*   **ingestId**

    Pass the ingestId used to identify the ingest metric record

**Additional Notes**

You can also pass the arguments as a JSON string and 
post to the URL.  The format supported:

```
{
  "ingestId": "",
}
```
            """)
    @ApiImplicitParams( [
            @ApiImplicitParam( name = 'ingestId', value = 'ID Used to identify the ingest record', dataType = 'string', paramType="query", required = true ),
    ] )
    def endIngest(){

        def jsonData = request.JSON?request.JSON as HashMap:null
        def requestParams = params - params.subMap( ['controller', 'action'] )
        def cmd = new IngestCommand()
        if(jsonData) requestParams << jsonData

        // get map from JSON and merge into parameters
        if(jsonData) requestParams << jsonData
        BindUtil.fixParamNames( IngestCommand, requestParams )
        bindData( cmd, requestParams )

        HashMap result = ingestMetricsService.endIngest(cmd)
        String resultString =  (result as JSON).toString()
        response.status      = result.statusCode
        response.contentLength = resultString.length()


        render contentType: "application/json", text: resultString
    }
    @ApiOperation( value = "Marks the start of the copy process",
            produces = 'text/plain',
            httpMethod = 'POST',
            notes = """
The service api **startCopy** marks the start of a copy operation. This might include just copying the files into a different location or downloading from a remote location. This will use the current date and time to mark the time of the call  

## Parameter List

*   **ingestId**

    Pass the ingestId used to identify the ingest metric record

**Additional Notes**

You can also pass the arguments as a JSON string and 
post to the URL.  The format supported:

```
    {
      "ingestId": "",
    }
```
    """)
    @ApiImplicitParams( [
            @ApiImplicitParam( name = 'ingestId', value = 'ID Used to identify the ingest record', dataType = 'string', paramType="query", required = true ),
    ] )
    def startCopy(){
        def jsonData = request.JSON?request.JSON as HashMap:null
        def requestParams = params - params.subMap( ['controller', 'action'] )
        def cmd = new IngestCommand()
        if(jsonData) requestParams << jsonData

        // get map from JSON and merge into parameters
        if(jsonData) requestParams << jsonData
        BindUtil.fixParamNames( IngestCommand, requestParams )
        bindData( cmd, requestParams )

        HashMap result  = ingestMetricsService.startCopy(cmd)
        String resultString =  (result as JSON).toString()
        response.status      = result.statusCode
        response.contentLength = resultString.length()


        render contentType: "application/json", text: resultString
    }
    @ApiOperation( value = "Marks the end of the copy process",
            produces = 'text/plain',
            httpMethod = 'POST',
            notes = """
The service api **startCopy** marks the end of a copy operation. This will use the current date and time to mark the time of the call  

## Parameter List

*   **ingestId**

    Pass the ingestId used to identify the ingest metric record

**Additional Notes**

You can also pass the arguments as a JSON string and 
post to the URL.  The format supported:

```
{
    "ingestId": "",
}
```
            """)
    @ApiImplicitParams( [
            @ApiImplicitParam( name = 'ingestId', value = 'ID Used to identify the ingest record', dataType = 'string', paramType="query", required = true ),
    ] )
    def endCopy(){
        def jsonData = request.JSON?request.JSON as HashMap:null
        def requestParams = params - params.subMap( ['controller', 'action'] )
        def cmd = new IngestCommand()
        if(jsonData) requestParams << jsonData

        // get map from JSON and merge into parameters
        if(jsonData) requestParams << jsonData
        BindUtil.fixParamNames( IngestCommand, requestParams )
        bindData( cmd, requestParams )

        HashMap result  = ingestMetricsService.endCopy(cmd)
        String resultString =  (result as JSON).toString()
        response.status      = result.statusCode
        response.contentLength = resultString.length()


        render contentType: "application/json", text: resultString
    }
    @ApiOperation( value = "Marks the start of the staging process",
            produces = 'text/plain',
            httpMethod = 'POST',
            notes = """
The service api **startStaging** marks the start of a 
staging operation. This will include the creation of 
any needed overviews, histograms or anyother files that 
might need to be pre calaculated. This will use 
the current date and time to mark the time of the call  

## Parameter List

*   **ingestId**

    Pass the ingestId used to identify the ingest metric record

**Additional Notes**

You can also pass the arguments as a JSON string and 
post to the URL.  The format supported:

```
    {
        "ingestId": ""
    }
```
            """)
    @ApiImplicitParams( [
            @ApiImplicitParam( name = 'ingestId', value = 'ID Used to identify the ingest record', dataType = 'string', paramType="query", required = true ),
    ] )
    def startStaging(){
        def jsonData = request.JSON?request.JSON as HashMap:null
        def requestParams = params - params.subMap( ['controller', 'action'] )
        def cmd = new IngestCommand()
        if(jsonData) requestParams << jsonData

        // get map from JSON and merge into parameters
        if(jsonData) requestParams << jsonData
        BindUtil.fixParamNames( IngestCommand, requestParams )
        bindData( cmd, requestParams )

        HashMap result  = ingestMetricsService.startStaging(cmd)
        String resultString =  (result as JSON).toString()
        response.status     = result.statusCode
        response.contentLength     = resultString.length()


        render contentType: "application/json", text: resultString
    }

    @ApiOperation( value = "Marks the end of the staging process",
            produces = 'text/plain',
            httpMethod = 'POST',
            notes = """
The service api **endStaging** marks the end of a staging operation. This will use the current date and time to mark the time of the call  

## Parameter List

*   **ingestId**

    Pass the ingestId used to identify the ingest metric record

**Additional Notes**

You can also pass the arguments as a JSON string 
and post to the URL.  The format supported:

```        
    {
        "ingestId": "",
    }
```
            """)
    @ApiImplicitParams( [
            @ApiImplicitParam( name = 'ingestId', value = 'ID Used to identify the ingest record', dataType = 'string', paramType="query", required = true ),
    ] )
    def endStaging(){
        def jsonData = request.JSON?request.JSON as HashMap:null
        def requestParams = params - params.subMap( ['controller', 'action'] )
        def cmd = new IngestCommand()
        if(jsonData) requestParams << jsonData

        // get map from JSON and merge into parameters
        if(jsonData) requestParams << jsonData
        BindUtil.fixParamNames( IngestCommand, requestParams )
        bindData( cmd, requestParams )

        HashMap result  = ingestMetricsService.endStaging(cmd)
        String resultString =  (result as JSON).toString()
        response.status     = result.statusCode
        response.contentLength     = resultString.length()


        render contentType: "application/json", text: resultString
    }

    @ApiOperation( value = "Allows one to save/modify a record directly into the database",
            produces = 'text/plain',
            httpMethod = 'POST',
            notes = """
## Parameter List

*   **ingestId**

    Pass the ingestId used to identify the ingest 
    metric record

*   **newIngestId**

    Used to allow one to change the name of the indexId.
    You mus specify both indexId and newIndexId to change an indexId.

*   **description**

    Basic description used for this record.

*   **startDate**

    specify the start for date of the entire ingest 
    process. The date is in the format of an ISO date 
    standard. For example: 2016-01-01T00:00:00.001Z

*   **endDate**

    specify the end for date of the entire ingest process. 
    The date is in the format of an ISO date standard. For 
    example: 2016-01-01T00:01:00.001Z

*   **startCopy**

    specify the start date for the copy. The date is in the format of an ISO date 
    standard. For example: 2016-01-01T00:01:00.001Z

*   **endCopy**

    specify the end date for the copy. The date is in the 
    format of an ISO date standard. 
    For example: 2016-01-01T00:01:00.001Z

*   **startStaging**

    specify the start date for the staging. The date is in 
    the format of an ISO date standard. For example: 2016-01-01T00:01:00.001Z

*   **endStaging**

    specify the end date for the staging. The date is in 
    the format of an ISO date standard. For example: 2016-01-01T00:01:00.001Z

*   **status**

    Can be RUNNING, FINISHED, or FAILED

*   **statusMessage**

**Additional Notes**

You can also pass the arguments as a JSON string and 
post to the URL.  The format supported:

```
    {
      "ingestId": "",
      "newIngestId": "",
      "ingestId": "",
      "description": "",
      "startDate": "",
      "endDate": "",
      "startCopy": "",
      "endCopy": "",
      "startStaging": "",
      "endStaging": "",
      "status": "",
      "statusMessage": "",
    }
``` 
    """)
    @ApiImplicitParams( [
            @ApiImplicitParam( name = 'ingestId', value = 'ID Used to identify the ingest record', dataType = 'string', paramType="query", required = true ),
            @ApiImplicitParam( name = 'newIngestId', value = 'Used to change an ingestId to another ID', dataType = 'string', paramType="query", required = false ),
            @ApiImplicitParam( name = 'description', value = 'Set the description of the ingestId', dataType = 'string', paramType="query", required = false ),
            @ApiImplicitParam( name = 'startDate', value = 'Set the start date using a formatted ISO standard', dataType = 'string', paramType="query", required = false ),
            @ApiImplicitParam( name = 'endDate', value = 'Set the end date using a formatted ISO standard', dataType = 'string', paramType="query", required = false ),
            @ApiImplicitParam( name = 'startCopy', value = 'Set the start copy date using a formatted ISO standard', dataType = 'string', paramType="query", required = false ),
            @ApiImplicitParam( name = 'endCopy', value = 'Set the end copy date using a formatted ISO standard', dataType = 'string', paramType="query", required = false ),
            @ApiImplicitParam( name = 'startStaging', value = 'Set the start staging date using a formatted ISO standard', dataType = 'string', paramType="query", required = false ),
            @ApiImplicitParam( name = 'endStaging', value = 'Set the end staging date using a formatted ISO standard', dataType = 'string', paramType="query", required = false ),
            @ApiImplicitParam( name = 'status', allowableValues="[RUNNING, FINISHED, FAILED]", value = 'set the status', dataType = 'string', paramType="query", required = false ),
            @ApiImplicitParam( name = 'statusMessage', value = 'set the status message', dataType = 'string', paramType="query", required = false ),
    ] )
    def save(){
        def jsonData = request.JSON?request.JSON as HashMap:null
        def requestParams = params - params.subMap( ['controller', 'action'] )
        def cmd = new IngestCommand()
        if(jsonData) requestParams << jsonData

        // get map from JSON and merge into parameters
        if(jsonData) requestParams << jsonData
        BindUtil.fixParamNames( IngestCommand, requestParams )
        bindData( cmd, requestParams )

        HashMap result  = ingestMetricsService.save(cmd)
        String resultString =  (result as JSON).toString()
        response.status     = result.statusCode
        response.contentLength     = resultString.length()


        render contentType: "application/json", text: resultString
    }

    @ApiOperation( value = "Allows one to delete records from the ingest database",
            produces = 'text/plain',
            httpMethod = 'POST',
            notes = """
Delete records from the database. If no parameters are specified then all records are removed. If the ingestId is specified then just that ingest ID is removed. If start and end date is specified then only value between and including the dates are deleted. If the end date only is specified then anything including or less than that date is deleted. If startDate only then anything greater or equal to that date is deleted  

## Parameter List

*   **ingestId**

    Pass the ingestId used to identify the ingest metric record. This will allow one to delete a record by the ingestId

*   **startDate**

    Specify the startDate. If the startDate is specified and the end date is not then it will delete all records equal to and after the date specified. The date is in the format of an ISO date standard. For example: 2016-01-01T00:00:00.001Z

*   **endDate**

    Specify the endDate. If the startDate is specified and the end date is specified then it will delete all records between and including the two dates. The date is in the format of an ISO date standard. For example: 2016-01-01T00:00:00.001Z

**Additional Notes**
        
You can also pass the arguments as a JSON string and 
post to the URL.  The format supported:

```
    {
      "ingestId": "",
      "startDate": "",
      "endDate": "",
    }
```
            """)
    @ApiImplicitParams( [
            @ApiImplicitParam( name = 'ingestId', value = 'ID Used to identify the ingest record', dataType = 'string', paramType="query", required = false ),
            @ApiImplicitParam( name = 'startDate', value = 'Start date for the date range', dataType = 'string', paramType="query", required = false ),
            @ApiImplicitParam( name = 'endDate', value = 'End date for the date range', dataType = 'string', paramType="query", required = false ),
    ] )
    def delete()
    {
        def jsonData = request.JSON?request.JSON as HashMap:null
        def requestParams = params - params.subMap( ['controller', 'action'] )
        def cmd = new DeleteCommand()
        if(jsonData) requestParams << jsonData

        // get map from JSON and merge into parameters
        if(jsonData) requestParams << jsonData
        BindUtil.fixParamNames( DeleteCommand, requestParams )
        bindData( cmd, requestParams )

        HashMap result  = ingestMetricsService.delete(cmd)
        String resultString =  (result as JSON).toString()
        response.status     = result.statusCode
        response.contentLength     = resultString.length()

        render contentType: "application/json", text: resultString
    }
    @ApiOperation( value = "Allows one to list records from the ingest database",
            produces = 'text/plain',
            httpMethod = 'POST',
            notes = """
list records from the database. If no parameters are specified then all records are listed based on a pagination maximum. If the ingestId is specified then just that ingest ID is listed. If start and end date is specified then only value between and including the dates are listed. If the end date only is specified then anything including or less than that date is listed. If startDate only then anything greater or equal to that date is listed  

## Parameter List

*   **ingestId**

    Pass the ingestId used to identify the ingest metric record. This will allow one to list a record by the ingestId

*   **startDate**

    Specify the startDate. If the startDate is specified and the end date is not then it will delete all records equal to and after the date specified. The date is in the format of an ISO date standard. For example: 2016-01-01T00:00:00.001Z

*   **endDate**

    Specify the endDate. If the startDate is specified and the end date is specified then it will delete all records between and including the two dates. The date is in the format of an ISO date standard. For example: 2016-01-01T00:00:00.001Z

*   **limit**

    number of records to limit in the result set

*   **offset**

    number of records to offset by

**Additional Notes**

You can also pass the arguments as a JSON string and post 
to the URL.  The format supported:

```        
    {
      "ingestId": "",
      "startDate": "",
      "endDate": "",
      "limit": 100,
      "offset": 0,
    }
```    
        """)
    @ApiImplicitParams( [
            @ApiImplicitParam( name = 'ingestId', value = 'ID Used to identify the ingest record', dataType = 'string', paramType="query", required = false ),
            @ApiImplicitParam( name = 'startDate', value = 'Start date for the date range', dataType = 'string', paramType="query", required = false ),
            @ApiImplicitParam( name = 'endDate', value = 'End date for the date range', dataType = 'string', paramType="query", required = false ),
            @ApiImplicitParam( name = 'limit', value = 'Max records to return', defaultValue= "10", dataType = 'integer', paramType="query", required = false ),
            @ApiImplicitParam( name = 'offset', value = 'End date for the date range',  defaultValue= "0", dataType = 'string', paramType="query", required = false ),
            @ApiImplicitParam(name = 'sortBy', value = 'Sort by', paramType = 'query', defaultValue="startDate", dataType = 'string', paramType="query", ),
            @ApiImplicitParam(name = 'order', value = 'Order', defaultValue="desc", allowableValues="[asc, desc]", paramType = 'query', dataType = 'string', paramType="query", required=false)
    ] )
    def list()
    {
        def jsonData = request.JSON?request.JSON as HashMap:null
        def requestParams = params - params.subMap( ['controller', 'action'] )
        ListCommand cmd = new ListCommand()
        if(jsonData) requestParams << jsonData

        // get map from JSON and merge into parameters
        if(jsonData) requestParams << jsonData
        BindUtil.fixParamNames( ListCommand, requestParams )
        bindData( cmd, requestParams )

        HashMap result  = ingestMetricsService.list(cmd)
        String resultString    =  (result as JSON).toString()
        response.status        = result.statusCode
        response.contentLength = resultString.length()

        render contentType: "application/json", text: resultString
    }
    @ApiOperation( value = "Allows one to show a summary for the records from the ingest database",
            produces = 'text/plain',
            httpMethod = 'POST',
            notes = """
The summary will output duration times. You can 
summarize individual rows or it will sum up and entire 
ange and show average times for each stage of the ingest
process and the overall times. **Currently all time durations are in seconds**  

## Parameter List

*   **ingestId**

    Pass the ingestId used to identify the ingest metric record. This will allow one to list a record by the ingestId

*   **startDate**

    Specify the startDate. If the startDate is specified and the end date is not then it will delete all records equal to and after the date specified. The date is in the format of an ISO date standard. For example: 2016-01-01T00:00:00.001Z

*   **endDate**

    Specify the endDate. If the startDate is specified and the end date is specified then it will delete all records between and including the two dates. The date is in the format of an ISO date standard. For example: 2016-01-01T00:00:00.001Z

*   **individual**

    Boolean value true, false. This specifies if you want to do a summary on each individual record instead of summing them all together.

*   **limit**

    number of records to limit in the result set

*   **offset**

    number of records to offset by

**Additional Notes**

You can also pass the arguments as a JSON string and 
post to the URL.  The format supported:

```        
    {
      "ingestId": "",
      "startDate": "",
      "endDate": "",
      "individual": true,
      "limit": 100,
      "offset": 0,
    }
```
            """)
    @ApiImplicitParams( [
            @ApiImplicitParam( name = 'ingestId', value = 'ID Used to identify the ingest record', dataType = 'string', paramType="query", required = false ),
            @ApiImplicitParam( name = 'startDate', value = 'Start date for the date range', dataType = 'string', paramType="query", required = false ),
            @ApiImplicitParam( name = 'endDate', value = 'End date for the date range', dataType = 'string', paramType="query", required = false ),
            @ApiImplicitParam( name = 'individual', allowableValues="[true,false]", defaultValue="false", value = 'Flag to specify whether to do an individual sumary', dataType = 'boolean', paramType="query", required = false ),
            @ApiImplicitParam( name = 'limit', value = 'Max records to return', defaultValue= "10", dataType = 'integer', paramType="query", required = false ),
            @ApiImplicitParam( name = 'offset', value = 'End date for the date range',  defaultValue= "0", dataType = 'string', paramType="query", required = false ),
    ] )
    def summary()
    {
        def jsonData = request.JSON?request.JSON as HashMap:null
        def requestParams = params - params.subMap( ['controller', 'action'] )
        SummaryCommand cmd = new SummaryCommand()
        if(jsonData) requestParams << jsonData

        // get map from JSON and merge into parameters
        if(jsonData) requestParams << jsonData
        BindUtil.fixParamNames( SummaryCommand, requestParams )
        bindData( cmd, requestParams )

        HashMap result  = ingestMetricsService.summary(cmd)
        String resultString =  (result as JSON).toString()
        response.status     = result.statusCode
        response.contentLength     = resultString.length()


        render contentType: "application/json", text: resultString
    }
}
