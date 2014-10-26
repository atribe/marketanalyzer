/**
 * 
 */

var chartData= [
                {date: new Date(2011, 5, 1, 0, 0, 0, 0), val:10},
                {date: new Date(2011, 5, 2, 0, 0, 0, 0), val:11},
                {date: new Date(2011, 5, 3, 0, 0, 0, 0), val:12},
                {date: new Date(2011, 5, 4, 0, 0, 0, 0), val:11},
                {date: new Date(2011, 5, 5, 0, 0, 0, 0), val:10},
                {date: new Date(2011, 5, 6, 0, 0, 0, 0), val:11},
                {date: new Date(2011, 5, 7, 0, 0, 0, 0), val:13},
                {date: new Date(2011, 5, 8, 0, 0, 0, 0), val:14},
                {date: new Date(2011, 5, 9, 0, 0, 0, 0), val:17},
                {date: new Date(2011, 5, 10, 0, 0, 0, 0), val:13}
            ];

AmCharts.ready(function(){
	   //alert('page loaded');
	   
	   var chart = new AmCharts.AmStockChart();
	   chart.pathToImages = "js/amcharts/images/";
	   var dataSet = new AmCharts.DataSet();
       
	   dataSet.dataProvider = chartData;
       dataSet.fieldMappings = [{fromField:"val", toField:"value"}];   
       dataSet.categoryField = "date";          
       chart.dataSets = [dataSet];
       
       var stockPanel = new AmCharts.StockPanel();
       chart.panels = [stockPanel];
       
       var legend = new AmCharts.StockLegend();
       stockPanel.stockLegend = legend;
       
       var panelsSettings = new AmCharts.PanelsSettings();
       panelsSettings.startDuration = 1;
       chart.panelsSettings = panelsSettings;
       
       var graph = new AmCharts.StockGraph();
       graph.valueField = "value";
       graph.type = "column";
       graph.title = "MyGraph";
       graph.fillAlphas = 1;
       stockPanel.addStockGraph(graph);

       var categoryAxesSettings = new AmCharts.CategoryAxesSettings();
       categoryAxesSettings.dashLength = 5;
       chart.categoryAxesSettings = categoryAxesSettings;

       var valueAxesSettings = new AmCharts.ValueAxesSettings();
       valueAxesSettings .dashLength = 5;
       chart.valueAxesSettings  = valueAxesSettings;

       var chartScrollbarSettings = new AmCharts.ChartScrollbarSettings();
       chartScrollbarSettings.graph = graph;
       chartScrollbarSettings.graphType = "line";
       chart.chartScrollbarSettings = chartScrollbarSettings;

       var chartCursorSettings = new AmCharts.ChartCursorSettings();
       chartCursorSettings.valueBalloonsEnabled = true;
       chart.chartCursorSettings = chartCursorSettings;

       var periodSelector = new AmCharts.PeriodSelector();
       periodSelector.periods = [{period:"DD", count:1, label:"1 day"},
                                 {period:"DD", selected:true, count:5, label:"5 days"},
                                 {period:"MM", count:1, label:"1 month"},
                                 {period:"YYYY", count:1, label:"1 year"},
                                 {period:"YTD", label:"YTD"},
                                 {period:"MAX", label:"MAX"}];                
       chart.periodSelector = periodSelector;

       chart.write("OHLCChart");
	});