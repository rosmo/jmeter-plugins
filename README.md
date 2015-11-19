To install, copy created jar to lib/ext in jmeter directory and add these two lines to
bin/saveservice.properties:

```
DeterministicRandomOrderController=org.apache.jmeter.control.DeterministicRandomOrderController
DeterministicRandomOrderControllerGui=org.apache.jmeter.control.gui.DeterministicRandomOrderControllerGui
```
