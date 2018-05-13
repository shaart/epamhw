### Task 1 (20 points)
Implement horse race emulator using XML configuration

Required beans:
* <code>HorseService</code> for horse management (horses with their riders, breeds and other characteristics should be obtained from the configuration)
* <code>RaceService</code> for simple getRace() method which generates and returns information about the upcoming race (horses can be choosen randomly)
* <code>EmulationService</code> for real-time race emulation (it generates data about horses positions on the console for every second)

Main domain objects: <code>Horse</code>, <code>Race</code>, <code>Rider</code>, <code>Breed</code>.

Add client code:
* On start-up application shows the information about upcoming race.
* User is offered to choose his horse (make a bet) with searching by name, breed, rider’s name
* After that a race emulation starts
* Winner announcement is displayed
(Nothing super-fancy in UI, just console application)

### Installing
1. Clone repository `git clone https://github.com/shaart/epamhw.git`
2. Go to `horse-race-emulator` directory
3. Run <code>com.epam.Application</code>'s <code>main()</code>