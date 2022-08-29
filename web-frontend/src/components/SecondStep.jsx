export const SecondStep = ({dataFromForms}) => {
    return (
        <>
        Hello step 2!
        <button onClick={() => dataFromForms("hi", 3)}>Click me</button>
        </>
    );
}