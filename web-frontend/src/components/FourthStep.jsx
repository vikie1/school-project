export const FourthStep = ({dataFromForms}) => {
    return (
        <>
        Hello step 4!
        <button onClick={() => dataFromForms("hi", 1)}>Click me</button>
        </>
    );
}