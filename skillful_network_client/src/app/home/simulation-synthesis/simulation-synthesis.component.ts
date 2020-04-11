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
        if (this.simulation.exam == null) this.simulation.exam = MOCK_EXAM;
        if (this.simulation.examForm == null) this.simulation.examForm = MOCK_EXAM_FORM;
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
    if (this.simulation.exam != null && this.simulation.examForm != null) {
      console.log("############################");
      this.simulation.exam.exerciceSet.forEach((exercice) => {
        console.log("## exercice [" + exercice.id + "]");
        exercice.questions.forEach((question) => {
          console.log("## question [" + question.id + "] ");

          // look into ExamForm structure same questionId
          this.simulation.examForm.exerciceSet.forEach((exerciceForm) => {
            exerciceForm.questions.forEach((questionForm) => {
              // compare question ID
              if (questionForm.questionId == question.id) {
                console.log(
                  "## questionForm [" + questionForm.questionId + "] "
                );
                console.log("##++ userAnswer: " + questionForm.userAnswerId);
                question.userAnswerId = questionForm.userAnswerId;
              }
            });
          });
        });
      });
    }

    // if (this.simulation.exam != null) {
    //   console.log("############################");
    //   console.log("######## EXAM ##############");
    //   console.log("## [" + this.simulation.exam.id + "] ");
    //   this.simulation.exam.exerciceSet.forEach((element) => {
    //     console.log("######## SET ###############");
    //     console.log("## [" + element.id + "] " + element.name);
    //     element.questions.forEach((element) => {
    //       console.log("######## QUESTION ##########");
    //       console.log("## [" + element.id + "] " + element.question);
    //       element.choices.forEach((element) => {
    //         console.log("##-- [" + element.id + "] " + element.choice);
    //       });
    //       console.log("##++ correct: " + element.indexAnswer);
    //     });
    //   });
    //   console.log("############################");
    // } else {
    //   console.log("exam is undefined");
    // }

    // if (this.simulation.examForm != null) {
    //   console.log("############################");
    //   console.log("######## EXAM FORM #########");
    //   console.log("## [" + this.simulation.examForm.id + "] ");
    //   this.simulation.examForm.exerciceSet.forEach((element) => {
    //     console.log("############################");
    //     console.log("######## SET FORM ##########");
    //     console.log("## [" + element.id + "] ");
    //     element.questions.forEach((element) => {
    //       console.log("############################");
    //       console.log("######## QUESTION FORM #####");
    //       console.log("## [" + element.id + "] " + element.questionId);
    //       console.log("##++ userAnswer: " + element.userAnswerId);
    //     });
    //   });
    //   console.log("############################");
    // } else {
    //   console.log("exam is undefined");
    // }
  }
}
// async function wait (delay) {
//   await new Promise(resolve => setTimeout(resolve, delay));
// }
