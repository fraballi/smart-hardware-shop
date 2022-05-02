import { InjectionToken }                                            from '@angular/core';
import { ComponentFixture, TestBed }                                 from '@angular/core/testing';
import { MAT_DIALOG_DATA, MatDialog, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { DetailsComponent } from './details.component';

describe('DetailsComponent', () => {
  let component: DetailsComponent;
  let fixture: ComponentFixture<DetailsComponent>;

  beforeEach(async () => {

    await TestBed.configureTestingModule({
     imports:[MatDialogModule],
     providers: [
       {provide: MatDialog, useValue: {}},
       { provide: MAT_DIALOG_DATA, useValue: {} },
       { provide: MatDialogRef, useValue: {} }
     ],
      declarations: [ DetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
