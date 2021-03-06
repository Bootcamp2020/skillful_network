import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { UserService } from "../../shared/services/user.service";
import { Simulation } from "src/app/shared/models/simulation";
import { ApiHelperService } from "../../shared/services/api-helper.service";
import { MOCK_EXAM, MOCK_EXAM_FORM, } from "../../shared/models/mock.examen";

@Component({
  selector: "app-simulation-synthesis",
  templateUrl: "./simulation-synthesis.component.html",
  styleUrls: ["./simulation-synthesis.component.scss"],
})
export class SimulationSynthesisComponent implements OnInit {
  public componentName: String = "app-simulation-synthesis";
  public simulation: Simulation;
  public isLoaded: boolean = false;

  constructor(
    private api: ApiHelperService,
    private userService: UserService,
    private route: ActivatedRoute
  ) {}

  async ngOnInit() {
    // get simulatin Id from route parsing /home/simulation/{simID}
    const { simId } = this.route.snapshot.params;
    console.log(">> simId from route /home/simulation/" + simId + "}");

    await this.api
      .get({ endpoint: `/simulations/${simId}` })
      .then((data) => {
        this.simulation = data;
        // MOCK exam and simulationForm
        // if (this.simulation.exam == null) this.simulation.exam = MOCK_EXAM;
        // if (this.simulation.examForm == null) this.simulation.examForm = MOCK_EXAM_FORM;
        this.getUserAnswer();
      })
      .catch(() => {
        console.log(">> ERR unknown simulation.");
      })
      .finally(() => (this.isLoaded = true));
    this.simulationInfo();
  }

  public simulationInfo(): void {
    let info: String = ">>> " + this.componentName + ": simulation";
    if (!this.isLoaded) {
      info = info + " is undefined yet";
    } else {
      if (this.simulation == null) {
        info = info + " is not found!";
      } else {
        info = info + " is loaded! its id: " + this.simulation.id;
      }
    }
    console.log(info);
  }

  public getUserAnswer(): void {
    // insert user answer in exam entity
    console.log("############################");
    console.log("## exam: " + this.simulation.exam.id);
    console.log("## examForm: " + this.simulation.simulationForm.id);
    
    if (this.simulation.exam != null && this.simulation.simulationForm != null) {
      this.simulation.exam.exerciseSet.forEach((exercise) => {
        console.log("############################");
        console.log("## exercice [" + exercise.id + "] " + exercise.name);
        
        exercise.questions.forEach((question) => {
          console.log("############################");
          console.log("## question [" + question.id + "] ");
          console.log("## indexAnswer [" + question.indexAnswer + "] ");
          
          // look into ExamForm structure same questionId
          this.simulation.simulationForm.exerciseSet.forEach((exerciseForm) => {
            exerciseForm.answerSet.forEach((answerForm) => {
              // compare question ID
              if (answerForm.questionId == question.id) {
                console.log("##++ questionForm [" + answerForm.questionId + "] ");
                console.log("##++ userAnswer [" + answerForm.answer + "] ");
                question.userAnswerId = answerForm.answer;
              }
            });
          });
          
        });
      });
    }
  }
}
