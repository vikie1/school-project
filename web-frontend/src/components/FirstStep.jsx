export const FirstStep = ({dataFromForms}) => {
    return (
        <>
        Hello step 1!
        <button onClick={() => dataFromForms("hi", 2)}>Click me</button>
        </>
    );
}